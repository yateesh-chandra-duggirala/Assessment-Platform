import React from 'react';
import { FaHome, FaListAlt, FaPowerOff } from 'react-icons/fa';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import SweetAlert from '../SweetAlerts/SweetAlert';
import SignOutIcon from '../ButtonComponents/SignOutIcon';

function UserNavBar() {

    const location = useLocation();
    const navigate = useNavigate();

    const logOut = () =>{
        SweetAlert.signOutSweetAlert(
            () => {localStorage.clear();
                setTimeout(() => {
                    navigate("/login");
                }, 1500);
            }
        , "Signing out");
    }

    return (
        <div className="app">
            <header className="sticky">
                <nav>
                    <ul>
                        <li>
                            <Link to="/user" className={location.pathname === '/user' || location.pathname.startsWith('/manage') ? 'active' : ''}>
                                <div className='navbar-link'>
                                    <FaHome/> 
                                    <span className='text'>
                                        Home
                                    </span>
                                </div>
                            </Link>
                        </li>

                        <li>
                            <Link to="/profile" className={location.pathname === '/profile' ? 'active' : ''}>
                                <div className='navbar-link'>
                                    <FaListAlt/> 
                                    <span className='text'>
                                        Report
                                    </span>
                                </div>
                            </Link>
                        </li>

                        <div className='sign-out-button'>
                            <SignOutIcon className = "sign-button" onClick = {logOut} text = "Sign Out" icon={<FaPowerOff className='icon'/>}/>
                        </div>
                    </ul>
                </nav>
            </header>
        </div>
    );
}

export default UserNavBar;