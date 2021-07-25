import base64
from bs4 import BeautifulSoup
from github import Github
import requests
import json
from sendgrid import SendGridAPIClient
from sendgrid.helpers.mail import Mail, Personalization, Email, Content

from hide import BASE_URL, TWILIO_API_KEY, GITHUB_API_KEY

class GithubScraper:
    # Constructor to initialize the Github object with a target user and repo
    def __init__(self, user, repo):
        # May need another form of authentication
        self.g = Github(GITHUB_API_KEY)
        self.user = self.g.get_user(user)
        self.repo = self.user.get_repo(repo)
    
    # Check most recent commit for the specified file
    def checkFile(self, filename):
        commits = self.repo.get_commits()
        changedFiles = commits[0].files

        for f in changedFiles:
            if f.filename == filename:
                return True
        
        return False

    # Get the file specified by name in the most recent commit
    def getFile(self, filename):
        commits = self.repo.get_commits()
        targetFile = None

        # Get the correct file
        for f in commits[0].files: 
            if f.filename == filename:
                targetFile = f
                break
        
        return targetFile
    
    # Converts a markdown file into HTML text
    def getHTML(self, f):
        contents = requests.get(f.contents_url).text

        # Format the response from the Github API into a python dictionary
        gitResponse = json.loads(contents)

        # Decode the text from the markdown file into utf-8, and return the resulting html
        decodedContent = (base64.b64decode(gitResponse['content'])).decode('utf-8')
        htmlText = self.g.render_markdown(decodedContent, self.repo)
        return htmlText
    
    # Return the time of the most recent commit
    def getCommitTime(self):
        # Get the url of the most recent commit 
        commits = self.repo.get_commits()
        mostRecent = commits[0]

        # Dump json into a python dictionary
        contents = requests.get(mostRecent.url).text
        comDict = json.loads(contents)

        # Return the time the commit was made
        timeString = comDict['commit']['author']['date']
        return timeString
    
    def getRepoURL(self):
        return self.repo.html_url
        
# Backend API Functionality


# returns dict of most recent
def accessMostRecent():
    res = requests.get(BASE_URL + '/getMRP')
    return res.json()[-1]

# returns 200 if successful
def addMostRecent(company, role, link, location):
    res = requests.post(BASE_URL + '/addMRP', json={"company": company, "role": role, "link": link,"location": location})
    return res

# returns 200 if successful
def deleteMostRecent():
    res = requests.delete(BASE_URL + '/deleteMRP')
    return res

def getAllSubscribers():
    res = requests.get(BASE_URL + '/getUsers').json()
    subs = [i['email_address'] for i in res]
    return subs

def sendEmailAPI(mailingList: list[str], subject: str, message: str) -> None:
    mail = Mail()

    for to_email in mailingList:
        # Create new instance for each email
        personalization = Personalization()
        # Add email addresses to personalization instance
        personalization.add_to(Email(to_email))
        # Add personalization instance to Mail object
        mail.add_personalization(personalization)

    # Add data that is common to all personalizations
    mail.from_email = Email('internship.tracking2022@gmail.com')
    mail.subject = subject
    mail.add_content(Content('text/plain', message))

    # Send
    sg = SendGridAPIClient(TWILIO_API_KEY)
    response = sg.client.mail.send.post(request_body=mail.get())

def parseNotes(htmlText):
    links = htmlText.findAll('a')
    
    # If there are links in the notes section, return this string format
    if len(links) > 0:
        result = ''
        for link in links:
            result += (link.text + '\n')
        return result
    
    else:
        return htmlText.text


'''
{'id': 3, 'company': 'fortnite', 
'role': 'fortnite gamer', 
'link': 'fortnite.com', 
'date_added': '2021-07-24T22:39:21.453+00:00', 
'location': 'dirty docks'}
'''

def findDBEntry(dbEntry, rows):
    for i, row in enumerate(rows):
        columns = row.findAll('td')
        if len(columns) > 0:
            name = columns[0].a.text if columns[0].a else ''
            rowEntry = {'company': name, 'location': columns[1].text, 'role': parseNotes(columns[2])}
            
            if matchingEntries(dbEntry, rowEntry):
                return i
        else:
            continue
    
    return -1

def matchingEntries(dbEntry, rowEntry):
    return rowEntry['company'] == dbEntry['company'] and rowEntry['location'] == dbEntry['location'] and rowEntry['role'] == dbEntry['role']

def main():
    # Initialize the scraper
    scraper = GithubScraper('pittcsc', 'Summer2022-Internships')

    # Check if the README was updated

    # Initialize the readme and the BeautifulSoup instance
    readme = scraper.getFile('README.md')
    htmlText = scraper.getHTML(readme)
    soup = BeautifulSoup(htmlText, 'lxml')

    # Get the table and the rows
    tableBody = soup.find('table')
    rows = tableBody.findAll('tr')

    # Get the most recent entry in the database
    dbEntry = accessMostRecent()

    # 1. Find db entry 
    # 2. Once we find db entry, create subarray from dbEntry_index + 1 to the end
    # 3. If len(subarray) == 0 then do nothing --> Implies that the last entry matches the one in the db
    # 4. Otherwise find all companies, send the email, and update db  
    dbIndex = findDBEntry(dbEntry, rows)

    # Retrieve the last entry from the table
    latest = rows[len(rows) - 1]
    columns = latest.findAll('td')
    compName = columns[0].a.text if columns[0].a else ''
    notesPlainText = parseNotes(columns[2])
    lastEntry = {'company': compName, 'location': columns[1].text, 'role': notesPlainText}

    if dbIndex == -1:
        deleteMostRecent()
        addMostRecent(lastEntry['company'], lastEntry['role'], 'null', lastEntry['location'])
        return
    
    remainingRows = rows[(dbIndex + 1):]
    newCompanies = []

    if len(remainingRows) == 0:
        return

    for row in remainingRows:
        columns = row.findAll('td')
        compName = columns[0].a.text if columns[0].a else ''

        if not compName == '':
            newCompanies.append(compName)

    print(newCompanies)

    # Update DB
    deleteMostRecent()
    addMostRecent(lastEntry['company'], lastEntry['role'], 'null', lastEntry['location'])

    # Send emails
    subscribers = getAllSubscribers()
    subject = generateSubject(newCompanies)
    body = generateBody(newCompanies, scraper.getRepoURL())

    sendEmailAPI(subscribers, subject, body)

    print(subject)
    print('\n')
    print(body)

def generateSubject(companies):
    # New Postings from {First Company} and more!
    result = ''
    if len(companies) > 1:
        result = result + f'New Postings from {companies[0]} and More!'
    else:
        result = result + f'New Posting from {companies[0]}!'

    return result

def generateBody(companies, url):
    body = 'New Positions Added: \n \n' if len(companies) > 1 else 'New Position Added: \n \n'
    for company in companies:
        sanitized = sanitizeCompany(company)
        body += ('- ' + sanitized + '\n')
    
    body += f'\nSee the full list here for more information: {url}\n'
    body += '\nGood Luck!'

    return body

def sanitizeCompany(company):
    result = company.split(' ')
    capitalized = [i.capitalize() for i in result]
    return " ".join(capitalized)

main()