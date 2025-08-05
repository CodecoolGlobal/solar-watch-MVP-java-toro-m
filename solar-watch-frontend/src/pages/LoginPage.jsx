import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
        if (error) setError('');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("/api/user/login", {
                method: "PATCH",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    username: formData.username,
                    password: formData.password
                })
            });

            if (!response.ok) {
                const errorData = await response.json().catch(() => ({}));
                throw new Error(errorData.message || "Login failed. Please check your credentials and try again.");
            }

            const token = await response.text();
            localStorage.setItem("token", token);
            navigate('/tables');

        } catch (error) {
            console.error("Login error:", error);
            setError(error.message || "An error occurred during login. Please try again.");
        }
    };

    return (
        <div>
            <h2>Login</h2>
            {error && <div>{error}</div>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:
                        <input
                            type="text"
                            name="username"
                            value={formData.username}
                            onChange={handleChange}
                            required
                        />
                    </label>
                </div>
                <div>
                    <label>Password:
                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </label>
                </div>
                <button type="submit">Login</button>
            </form>
            <button type="button" onClick={() => navigate('/register')}>
                Don't have an account? Register
            </button>
        </div>
    );
};

export default LoginPage;