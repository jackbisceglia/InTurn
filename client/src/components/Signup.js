import { useState } from "react";

const BASE_URL = 'https://apptrack-backend.ue.r.appspot.com';

function Signup() {
    const [emailInput, setEmailInput] = useState('');
    const [message, setMessage] = useState('');
    const [showMessage, setShowMessage] = useState(false);

    // TODO: Cleanup
    function submitHandler(e) {
        e.preventDefault();
        if (emailInput === '') {
            setMessage('Must enter an email');
            setShowMessage(true);
            setTimeout(() => { setShowMessage(false); setMessage('') }, 4000);
            return;
        }
        let postUrl = BASE_URL + '/addUser'
        let formData = { email_address: emailInput };
        fetch(postUrl, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }).then(response => {
            if (response.status === 200) {
                // show success
                setMessage('Success!');
            } else {
                // show unsuccessful
                setMessage('Error, Try Again Later');
            }
            setShowMessage(true);
            setTimeout(() => { setShowMessage(false); setMessage('') }, 4000);
            setEmailInput('');
        });
    }

    return (
        <div className="dual-container">
            <div className="headmsg-container">
                <h1 className="dual-title" id="form-header">SIGN UP</h1>
                {showMessage ? <span id="status-msg" style={message === 'Success!' ? { color: '#65FFDB' } : { color: 'red' }}>{message}</span> : <></>}
            </div>
            <form onSubmit={(e) => submitHandler(e)} action="">
                <div className="input-container">
                    <input value={emailInput} id="email" className="input" type="email" placeholder="Email"
                        onChange={(e) => setEmailInput(e.target.value)} />
                </div>
                <button type="text" className="submit">Sign Up</button>
            </form>
        </div>
    );
}

export default Signup;