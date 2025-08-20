import { useState } from 'react';

const SunDataForm = ({ onFormSubmit }) => {
    const [formData, setFormData] = useState({
        city: '',
        date: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onFormSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>City:
                    <input
                        type="text"
                        name="city"
                        value={formData.city}
                        onChange={handleChange}
                        required
                    />
                </label>
            </div>
            <div>
                <label>Date:
                    <input
                        type="date"
                        name="date"
                        value={formData.date}
                        onChange={handleChange}
                        required
                    />
                </label>
            </div>
            <button type="submit">Get Sun Data</button>
        </form>
    );
};

export default SunDataForm;