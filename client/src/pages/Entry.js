import { useState, useEffect } from 'react';
import Home from './Home'
import Unsubscribe from './Unsubscribe'
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";


// Root Level Routing
export default function Entry() {
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        setLoading(prevState => !prevState)
    }, [])

    return loading ? <h1>Loading... </h1> : <Routing/>
}

function Routing() {
    return (
        <Router>
            <Switch >
                <Route path="/unsubscribe/:userIdentifier"><Unsubscribe /></Route>
                <Route path="/" ><Home /></Route>
            </Switch>
        </Router>
    )
}