import { useParams, Link } from "react-router-dom";
import '../styles/Entry.css';

export default function Unsubscribe() {
    let { userIdentifier } = useParams();

    // WRITE THIS FUNCTION
    const handleUnsub = () => {
        console.log(userIdentifier)
    }

    const wrap =  {
        margin: 0, padding: 0,
        display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', 
        width: '100%', height: '100%'
    }

    const pStyle = {fontSize: '0.9rem', margin: 0, padding: 0}

    return (
        <div className="wrapper" style={wrap}>
            <button onClick={() => handleUnsub()} style={{width: '60%', marginBottom: '1rem', padding: 0}} className="submit">Confirm Unsubscribe</button>
            <p style={pStyle} >You will no longer receive emails from us concerning internship postings.</p>
            <p style={pStyle}>Head to <Link to="/">our home page</Link> if you'd like to re-subscribe in the future</p>
        </div> 
    )
}
