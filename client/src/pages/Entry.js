import '../styles/Entry.css';

import Header from '../components/Header';
import About from '../components/About';
import Signup from '../components/Signup';

function Entry() {
  return (
    <>
      <div className="wrapper">
        <Header />
        <About />
        <Signup />
      </div>
    </>
  );
}

export default Entry;