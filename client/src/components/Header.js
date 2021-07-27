import '../styles/Entry.css'

function Header() {
    return (
        <div className="header-wrapper">
            <div className="head">
                <img className="icon" src="./imgs/code.svg" alt="" />
                <h1 className="header-title">InTurn</h1>
            </div>
            <p>created by <a rel="noreferrer" target="_blank" href="https://github.com/jackbisceglia">Jack Bisceglia</a>, <a rel="noreferrer" target="_blank" href="https://github.com/joepetrillo">Joe Petrillo</a>, <a rel="noreferrer" target="_blank" href="https://github.com/ewu2023">Eric Wu</a> and <a rel="noreferrer" target="_blank" href="https://github.com/sid2033">Siddharth Raju</a></p>
        </div>
    );
}

export default Header;