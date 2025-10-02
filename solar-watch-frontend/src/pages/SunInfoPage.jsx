import { useState } from "react";
import {
    Container,
    Paper,
    Typography,
    Box,
    CircularProgress,
    Alert,
    Button
} from '@mui/material';
import SunDataForm from "../components/SunDataForm";
import SunDataRequestedInfo from "../components/SunDataRequestedInfo";


const SunInfoPage = () => {
    const [sunData, setSunData] = useState(null);
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    const handleFormSubmit = async (formData) => {
        try {
            setIsLoading(true);
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
                const errorData = await response.json().catch(() => ({}));
                throw new Error(errorData.message || 'Failed to fetch sun data');
            }

            const data = await response.json();
            setSunData(data);
            setError(null);
        } catch (err) {
            setError(err.message);
            setSunData(null);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <Container maxWidth="sm" sx={{ py: 4 }}>
            <Paper
                elevation={0}
                sx={{
                    p: 4,
                    borderRadius: 2,
                    border: '1px solid',
                    borderColor: 'divider',
                }}
            >
                <Typography
                    variant="h5"
                    component="h1"
                    align="center"
                    gutterBottom
                    color="primary"
                    sx={{ mb: 4, fontWeight: 500 }}
                >
                    Sun Information
                </Typography>

                {error && (
                    <Alert
                        severity="error"
                        sx={{
                            mb: 3,
                            '& .MuiAlert-message': {
                                width: '100%',
                                textAlign: 'center'
                            }
                        }}
                    >
                        {error}
                    </Alert>
                )}

                {isLoading ? (
                    <Box display="flex" justifyContent="center" my={4}>
                        <CircularProgress />
                    </Box>
                ) : !sunData ? (
                    <SunDataForm onFormSubmit={handleFormSubmit} />
                ) : (
                    <Box>
                        <SunDataRequestedInfo sunData={sunData} />
                        <Box mt={3} display="flex" justifyContent="center">
                            <Button
                                variant="outlined"
                                onClick={() => setSunData(null)}
                                sx={{
                                    textTransform: 'none',
                                    fontWeight: 500
                                }}
                            >
                                New Search
                            </Button>
                        </Box>
                    </Box>
                )}
            </Paper>
        </Container>
    );
};

export default SunInfoPage;