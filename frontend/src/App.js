import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import AdminDashBoard from '../src/pages/DashBoard/AdminDashBoard';
import HomePage from "../src/pages/HomePage/HomePage";
import Registration from '../src/pages/LoginRegistration/Registration';
import Login from '../src/pages/LoginRegistration/Login';
import UserDashBoard from '../src/pages/DashBoard/UserDashBoard';
import ManageCategory from '../src/pages/Categories/ManageCategory';
import AddOrUpdateCategory from "../src/pages/Categories/AddOrUpdateCategory";
import ManageQuiz from '../src/pages/Quiz/ManageQuiz';
import AddOrUpdateQuiz from '../src/pages/Quiz/AddOrUpdateQuiz';
import ManageQuestion from '../src/pages/Question/ManageQuestion';
import Profile from '../src/pages/Profile/Profile';
import Test from '../src/pages/Question/Test';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path = "/" element = {<HomePage/>}/>
          <Route path = "/register" element = {<Registration/>}/>
          <Route path = "/login" element = {<Login/>}/>
          <Route path = "/admin" element = {<AdminDashBoard/>}/>
          <Route path = "/profile" element = {<Profile/>}/>
          <Route path = "/manage-category" element = {<ManageCategory/>} />
          <Route path = "/add-category" element = {<AddOrUpdateCategory/>} />
          <Route path = "/add-category/:categoryId" element = {<AddOrUpdateCategory/>} />
          <Route path = "/manage-quiz/:categoryId" element = {<ManageQuiz/>} />
          <Route path = "/add-quiz" element = {<AddOrUpdateQuiz/>}/>
          <Route path = "/add-quiz/:quizId" element = {<AddOrUpdateQuiz/>}/>
          <Route path = "/manage-question/:quizId" element = {<ManageQuestion/>}/>
          <Route path = "/test/:quizId" element = {<Test/>}/>
          <Route path = "/user" element = {<UserDashBoard/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
