import '../styles/Entry.css';
import { Link } from "react-router-dom";

import Header from '../components/Header';
import About from '../components/About';
import Signup from '../components/Signup';

// Home Page for email list
function Home() {
  return (
    <>
      <div className="wrapper">
        <Header />
        <About />
        <Signup />
        <FootNotes />
      </div>
    </>
  );
}

// Helper Component to clean up the Render Tree in Home
// Unsubscribe is currently commented out until we implement our initial unsubscribe functionality
function FootNotes() {
  return (
    <>
      {/* <div className="tiny">
      Want to <Link to="/unsubscribe/send_reset">Unsubscribe</Link>?
      </div> */}
      <div className="tiny">Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
      </div>
    </>
  )
}

export default Home;