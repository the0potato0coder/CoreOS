const SubscriptionManagement = () => {
    const [activeSubscription, setActiveSubscription] = useState(null);
    const [subscriptionPlans] = useState([
      { id: 1, name: '30 Days', duration: 30, price: 50 },
      { id: 2, name: '90 Days', duration: 90, price: 140 },
      { id: 3, name: '180 Days', duration: 180, price: 260 },
      { id: 4, name: '360 Days', duration: 360, price: 480 }
    ]);
  
    const purchaseSubscription = async (planId) => {
      try {
        const response = await fetch('/api/customer/subscriptions', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            customerId: getUserId(), // Implement getUserId()
            planId: planId
          })
        });
        
        if (response.ok) {
          const data = await response.json();
          setActiveSubscription(data);
        }
      } catch (error) {
        console.error('Error purchasing subscription:', error);
      }
    };
  
    return (
      <div className="p-6">
        <h2 className="text-2xl font-bold mb-6">Subscription Management</h2>
        
        {activeSubscription ? (
          <div className="bg-white rounded-lg shadow p-6 mb-6">
            <h3 className="text-xl font-semibold mb-4">Active Subscription</h3>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <p className="text-gray-600">Plan</p>
                <p className="font-semibold">{activeSubscription.type}</p>
              </div>
              <div>
                <p className="text-gray-600">Expires On</p>
                <p className="font-semibold">
                  {new Date(activeSubscription.endDate).toLocaleDateString()}
                </p>
              </div>
            </div>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            {subscriptionPlans.map(plan => (
              <div key={plan.id} className="bg-white rounded-lg shadow p-6">
                <h3 className="text-xl font-semibold mb-2">{plan.name}</h3>
                <p className="text-3xl font-bold mb-4">${plan.price}</p>
                <Button 
                  onClick={() => purchaseSubscription(plan.id)}
                  className="w-full"
                >
                  Purchase
                </Button>
              </div>
            ))}
          </div>
        )}
      </div>
    );
  };