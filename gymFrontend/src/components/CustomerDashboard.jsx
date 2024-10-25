import React, { useState } from 'react';

const CustomerDashboard = () => {
    const [subscription, setSubscription] = useState(null);
  
    return (
      <div className="p-6">
        <h2 className="text-2xl font-bold mb-6">Customer Dashboard</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div className="bg-white p-6 rounded-lg shadow">
            <h3 className="text-xl font-semibold mb-4">Subscription Status</h3>
            {subscription ? (
              <div>
                <p>Type: {subscription.type}</p>
                <p>Valid until: {subscription.endDate}</p>
              </div>
            ) : (
              <p>No active subscription</p>
            )}
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <h3 className="text-xl font-semibold mb-4">Buy Subscription</h3>
            {/* Add subscription purchase form here */}
          </div>
        </div>
      </div>
    );
  };