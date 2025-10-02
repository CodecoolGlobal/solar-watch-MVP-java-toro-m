import React from 'react';
import { Box, Typography, Paper, Divider } from '@mui/material';

const SunDataRequestedInfo = ({ sunData, onBack }) => {
    const { city, date, sunriseTime: sunrise, sunsetTime: sunset } = sunData;
    const formattedDate = new Date(date).toLocaleDateString('en-US', {
        weekday: 'long',
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });

    return (
        <Box sx={{ width: '100%' }}>
            <Typography component="h2" variant="h6" sx={{ mb: 2, color: 'primary.main' }}>
                Sun Data for {city.name}
            </Typography>
            <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
                {formattedDate}
            </Typography>
            
            <Paper 
                elevation={2} 
                sx={{ 
                    p: 3, 
                    mb: 3, 
                    borderRadius: 2,
                    border: '1px solid',
                    borderColor: 'divider'
                }}
            >
                <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
                    <Box sx={{ textAlign: 'center', flex: 1 }}>
                        <Typography variant="subtitle2" color="text.secondary">SUNRISE</Typography>
                        <Typography variant="h5" sx={{ mt: 1, fontWeight: 500 }}>
                            {sunrise}
                        </Typography>
                    </Box>
                    
                    <Divider orientation="vertical" flexItem sx={{ mx: 2 }} />
                    
                    <Box sx={{ textAlign: 'center', flex: 1 }}>
                        <Typography variant="subtitle2" color="text.secondary">SUNSET</Typography>
                        <Typography variant="h5" sx={{ mt: 1, fontWeight: 500 }}>
                            {sunset}
                        </Typography>
                    </Box>
                </Box>
                
                <Divider sx={{ my: 2 }} />
                
                <Typography variant="body2" color="text.secondary" align="center">
                    Timezone: {sunData.timezoneId}
                </Typography>
            </Paper>
        </Box>
    );
};

export default SunDataRequestedInfo;