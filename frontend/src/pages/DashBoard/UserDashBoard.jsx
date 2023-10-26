import React from 'react';
import '../DashBoard/board.css'
import UserNavBar from '../../components/NavBars/UserNavBar';
import NotFound from '../HomePage/NotFound';
import DeactivateBackButton from '../../components/ButtonComponents/DeactivateBackButton';
import ManageCategory from '../Categories/ManageCategory';
import Header1 from '../../components/HeaderComponents/Header1';

function UserDashBoard() {

    const userRole = localStorage.getItem("role");
    const userName = localStorage.getItem("name");
    
    return (
        <div className="app">
            <DeactivateBackButton/>
            {userRole === "USER" ? 
            <> 
                <UserNavBar/>
                <main>
                    <Header1 text = {`Welcome ${userName}, You can click on the below categories and take test`} className = "h1-profile arial" />
                </main>
                <ManageCategory/>
            </> : <NotFound/>
            }
        </div>
    );
}

export default UserDashBoard;