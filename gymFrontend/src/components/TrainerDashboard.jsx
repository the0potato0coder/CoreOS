import React, { useState } from 'react';

const TrainerDashboard = () => {
    const [isClockIn, setIsClockIn] = useState(false);
  
    const handleClockInOut = async () => {
      // Add clock in/out logic here
      setIsClockIn(!isClockIn);
    };
  
    return (
      <div className="p-6">
        <h2 className="text-2xl font-bold mb-6">Trainer Dashboard</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div className="bg-white p-6 rounded-lg shadow">
            <h3 className="text-xl font-semibold mb-4">Attendance</h3>
            <button
              onClick={handleClockInOut}
              className={`p-3 rounded ${
                isClockIn ? 'bg-red-500' : 'bg-green-500'
              } text-white`}
            >
              {isClockIn ? 'Clock Out' : 'Clock In'}
            </button>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <h3 className="text-xl font-semibold mb-4">Add Customer</h3>
            {/* Add customer form here */}
          </div>
        </div>
      </div>
    );
  };