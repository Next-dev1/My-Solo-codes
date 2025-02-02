import React, { useState } from "react";
import "./App.css";

function App() {
  const [response, setResponse] = useState(null);
  const [buttonSize, setButtonSize] = useState({ yes: 1, no: 1 });

  const handleYes = () => {
    setResponse("I'LL BE SEEING YOU IN FEB 14 <3");
  };

  const handleNo = () => {
    setResponse(null);
    setButtonSize((prevSize) => ({
      yes: Math.min(prevSize.yes * 1.2, 50),
      no: Math.max(prevSize.no * 0.8, 0.1),
    }));
  };

  return (
    <div className="valentine-card">
      <h1>Will you be my Valentine?</h1>

      {response ? (
        <div className="response">
          <span role="img" aria-label="hearts">💖{" "}</span>
          {response}
          <span role="img" aria-label="hearts">{" "}💖</span>
          <div className="cat-gif">
            <img
              src="https://media.tenor.com/fz4_irvGQlcAAAAM/besito-catlove.gif"
              alt="Kissy cat"
            />
          </div>
        </div>
      ) : (
        <div className="buttons">
          <button
            className="yes-button"
            style={{ transform: `scale(${buttonSize.yes})` }}
            onClick={handleYes}
          >
            YES
          </button>
          <button
            className="no-button"
            style={{ transform: `scale(${buttonSize.no})` }}
            onClick={handleNo}
          >
            NO
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
