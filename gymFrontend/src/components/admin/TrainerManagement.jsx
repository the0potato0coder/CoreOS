import React, { useState, useEffect } from 'react';
import { Table } from '@/components/ui/table';
import { Button } from '@/components/ui/button';

const TrainerManagement = () => {
  const [trainers, setTrainers] = useState([]);
  const [showAddForm, setShowAddForm] = useState(false);
  const [newTrainer, setNewTrainer] = useState({
    fullName: '',
    email: '',
    phoneNumber: '',
    specialization: ''
  });

  useEffect(() => {
    fetchTrainers();
  }, []);

  const fetchTrainers = async () => {
    try {
      const response = await fetch('/api/admin/trainers');
      const data = await response.json();
      setTrainers(data);
    } catch (error) {
      console.error('Error fetching trainers:', error);
    }
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-2xl font-bold">Trainer Management</h2>
        <Button onClick={() => setShowAddForm(true)}>Add New Trainer</Button>
      </div>

      <Table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Specialization</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {trainers.map(trainer => (
            <tr key={trainer.id}>
              <td>{trainer.fullName}</td>
              <td>{trainer.email}</td>
              <td>{trainer.phoneNumber}</td>
              <td>{trainer.specialization}</td>
              <td>
                <Button variant="outline" size="sm" className="mr-2">
                  Edit
                </Button>
                <Button variant="destructive" size="sm">
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};
