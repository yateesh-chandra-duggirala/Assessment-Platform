import React from 'react';
import { FaCode, FaHome, FaListAlt, FaPowerOff } from 'react-icons/fa';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import SweetAlert from '../SweetAlerts/SweetAlert';
import SignOutIcon from '../ButtonComponents/SignOutIcon';

function AdminNavBar() {

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
                            <Link to="/admin" className={location.pathname === '/admin' ? 'active' : '' }>
                                <div className='navbar-link'>
                                    <FaHome />  
                                    <span className='text'>
                                        Home    
                                    </span>
                                </div>
                            </Link>
                        </li>

                        <li>
                            <Link to="/manage-category" className={location.pathname.startsWith('/add') || location.pathname.startsWith('/manage') ? 'active' : ''}>
                                <div className='navbar-link'>
                                    <FaCode/>
                                    <span className='text'>
                                        Categories
                                    </span>
                                </div>
                            </Link>
                        </li>

                        <li>
                            <Link to="/profile" className={location.pathname === '/profile' ? 'active' : ''}>
                                <div className='navbar-link'>
                                    <FaListAlt/> 
                                    <span className='text'>
                                        User Activity
                                    </span>
                                </div>
                            </Link>
                        </li>
                        <li className='sign-out-button'>
                            <SignOutIcon className = "sign-button" onClick = {logOut} text = "Sign Out" icon={<FaPowerOff className='icon'/>}/>
                        </li>
                    </ul>
                </nav>
            </header>
      </div>
    );
}

export default AdminNavBar;