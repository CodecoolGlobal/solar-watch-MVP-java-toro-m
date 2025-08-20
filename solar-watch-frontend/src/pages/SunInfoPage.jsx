import { useState } from "react";
import SunDataForm from "../components/SunDataForm";
import SunDataRequestedInfo from "../components/SunDataRequestedInfo";

const SunInfoPage = () => {
    const [sunData, setSunData] = useState(null);
    const [error, setError] = useState(null);

    const handleFormSubmit = async (formData) => {
        try {
            const token = JSON.parse(localStorage.getItem('token'))?.jwt;
            if (!token) {
                throw new Error('No authentication token found');
            }

            const response = await fetch(
                `http://localhost:5173/api/sun?location=${encodeURIComponent(formData.city)}&date=${formData.date}`,
                {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                }
            );

            if (!response.ok) {
                throw new Error('Failed to fetch sun data');
            }

            const data = await response.json();
            setSunData(data);
            setError(null);
        } catch (err) {
            setError(err.message);
            setSunData(null);
        }
    };

    return (
        <div>
            {!sunData ? (
                <SunDataForm onFormSubmit={handleFormSubmit} />
            ) : (
                <>
                    <SunDataRequestedInfo sunData={sunData} />
                    <button onClick={() => setSunData(null)}>Back</button>
                </>
            )}
            {error && <p>Error: {error}</p>}
        </div>
    );
};

export default SunInfoPage;