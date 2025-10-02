import { useState } from 'react';
import { TextField, Button, Box } from '@mui/material';

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
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1, width: '100%' }}>
            <TextField
                margin="normal"
                required
                fullWidth
                id="city"
                label="City"
                name="city"
                autoComplete="city"
                autoFocus
                value={formData.city}
                onChange={handleChange}
                sx={{ mb: 2 }}
            />
            <TextField
                margin="normal"
                required
                fullWidth
                name="date"
                label="Date"
                type="date"
                id="date"
                InputLabelProps={{
                    shrink: true,
                }}
                value={formData.date}
                onChange={handleChange}
                sx={{ mb: 3 }}
            />
            <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 2, mb: 2, py: 1.5 }}
            >
                Get Sun Data
            </Button>
        </Box>
    );
};

export default SunDataForm;