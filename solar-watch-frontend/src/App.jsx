import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import SunInfoPage from "./pages/SunInfoPage.jsx";

function App() {
    return (
        <Routes>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/suninfo" element={<SunInfoPage/>}/>
        </Routes>
    );
}

function AppWrapper() {
    return (
        <Router>
            <App/>
        </Router>
    );
}

export default AppWrapper;