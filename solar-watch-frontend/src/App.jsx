import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';

function App() {
  return (
      <Routes>
        <Route path="/login" element={<LoginPage />} />

      </Routes>
  );
}

function AppWrapper() {
  return (
      <Router>
        <App />
      </Router>
  );
}

export default AppWrapper;