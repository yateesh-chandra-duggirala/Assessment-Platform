import React, { useEffect, useState } from 'react';
import '../DashBoard/board.css'
import AdminNavBar from '../../components/NavBars/AdminNavBar';
import NotFound from '../HomePage/NotFound';
import UserServices from '../../services/UserServices';
import Header1 from '../../components/HeaderComponents/Header1';
import DeactivateBackButton from '../../components/ButtonComponents/DeactivateBackButton';

function AdminDashBoard() {
    const [users, setUsers] = useState([]);
    const [searchQuery,setSearchQuery] = useState('');
    const userRole = localStorage.getItem("role");

    useEffect(() => {
        fetchUsers();
    }, [])

    const fetchUsers = async () =>{
        await UserServices.getUsers()
        .then((response) => setUsers(response.data.body))
    }

    const filterUser = users.filter((users) => {
        return users.name.toLowerCase().includes(searchQuery.toLowerCase()) || users.email.toLowerCase().includes(searchQuery.toLowerCase());
    });

    const userRoleFilteredUsers = filterUser.filter((user) => user.userRole === "USER");

    return (
        <div className="app">
        <DeactivateBackButton/>
        {userRole === 'ADMIN' ? 
        <>
            <AdminNavBar/>
            <main>
                <Header1 text = "Welcome to the Admin Dashboard, Here are the Registered Users :" className = "h1-profile arial"/>
            </main>
            <div>
                <div className="admin-search-bar">
                    <input
                    className='search-input-over'
                    type="text"
                    placeholder="Search Users By Name or Email"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    />
                </div>

                <div className="category-table-container">
                    <table className="category-table">
                    {userRoleFilteredUsers.length !== 0 ? (
                        <>
                            <thead>
                                <tr>
                                    <th>Sno</th>
                                    <th>User Name</th>
                                    <th>Email</th>
                                    <th>Phone Number</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                {userRoleFilteredUsers.reverse().map((row, index) => (
                                <tr>
                                    <td>{index+1}</td>
                                    <td>{row.name}</td>
                                    <td>{row.email}</td>
                                    <td>{row.phoneNumber}</td>
                                </tr>
                                ))}
                            </tbody>
                        </>
                    ) : (
                        <Header1 className='text-22 arial' text = "No Users registered as of now." />
                    )}
                    </table>
                </div>
            </div>
        
        </> : <NotFound/>
    }
    </div>
    );
}

export default AdminDashBoard;