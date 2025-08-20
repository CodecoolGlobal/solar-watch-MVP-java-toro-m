import React from 'react';

const SunDataRequestedInfo = ({ sunData }) => {
  if (!sunData) return null;

  return (
    <div>
      <h2>Sun Data for {sunData.city.name}</h2>
      <p>Date: {sunData.date}</p>
      <div>
        <p>Sunrise: {sunData.sunriseTime}</p>
        <p>Sunset: {sunData.sunsetTime}</p>
      </div>
      <p>Timezone: {sunData.timezoneId}</p>
    </div>
  );
};

export default SunDataRequestedInfo;