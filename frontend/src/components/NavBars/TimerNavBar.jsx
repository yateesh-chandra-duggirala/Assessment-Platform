import React from 'react';
import { FaStopwatch } from 'react-icons/fa';
import { Link } from 'react-router-dom';

function TimerNavBar({timerValue, className}) {

    return (
        <div className="timer">
          <header>
              <nav>
                  <ul>
                      <li className='timer-sticky'>
                      <Link className = {`app ${className}`}><FaStopwatch/> Time Left : {timerValue}</Link>
                      </li>
                  </ul>
              </nav>
          </header>
      </div>
  );
}

export default TimerNavBar;
