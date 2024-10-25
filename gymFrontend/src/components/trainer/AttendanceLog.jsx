import React, { useState, useEffect } from 'react';
import { Table } from '@/components/ui/table';
import { Button } from '@/components/ui/button';

const AttendanceLog = () => {
    const [attendance, setAttendance] = useState([]);
    const [currentStatus, setCurrentStatus] = useState('OUT');
  
    useEffect(() => {
      fetchAttendanceLog();
    }, []);
  
    const handleClockInOut = async () => {
      try {
        const endpoint = currentStatus === 'OUT' ? '/api/trainer/clock-in' : '/api/trainer/clock-out';
        const response = await fetch(endpoint, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ trainerId: getUserId() }) // Implement getUserId()
        });
        
        if (response.ok) {
          setCurrentStatus(currentStatus === 'OUT' ? 'IN' : 'OUT');
          fetchAttendanceLog();
        }
      } catch (error) {
        console.error('Error updating attendance:', error);
      }
    };
  
    return (
      <div className="bg-white rounded-lg shadow p-6">
        <div className="flex justify-between items-center mb-6">
          <h3 className="text-xl font-semibold">Attendance Log</h3>
          <Button 
            onClick={handleClockInOut}
            variant={currentStatus === 'OUT' ? 'default' : 'destructive'}
          >
            Clock {currentStatus === 'OUT' ? 'In' : 'Out'}
          </Button>
        </div>
        
        <Table>
          <thead>
            <tr>
              <th>Date</th>
              <th>Clock In</th>
              <th>Clock Out</th>
              <th>Duration</th>
            </tr>
          </thead>
          <tbody>
            {attendance.map(record => (
              <tr key={record.id}>
                <td>{new Date(record.clockIn).toLocaleDateString()}</td>
                <td>{new Date(record.clockIn).toLocaleTimeString()}</td>
                <td>{record.clockOut ? new Date(record.clockOut).toLocaleTimeString() : '-'}</td>
                <td>{record.clockOut ? calculateDuration(record.clockIn, record.clockOut) : '-'}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  };