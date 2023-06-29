
    
    

    function displayModal(modalId) {
        var modal = document.getElementById(modalId);
        modal.style.display = "block";
    }

    // Function to close the modal
    function closeModal(modalId) {
        var modal = document.getElementById(modalId);
        modal.style.display = "none";
    }
   
    

    document.addEventListener('DOMContentLoaded', function () {
    	
        var cancelOrderButtons = document.querySelectorAll('.cancel-order-btn');
        var trackOrderButtons = document.querySelectorAll('.track-order-btn');
        
        cancelOrderButtons.forEach(function (button) {
            button.addEventListener('click', function () {
                var orderproId = this.getAttribute('data-orderproid');
                
                // Send an AJAX request to the server to cancel the order
                $.ajax({
                    url: 'cancelOrder',
                    type: 'POST',
                    data: { orderproId: orderproId },
                    success: function (response) {
                        // Update the shipment status in the frontend (if necessary)
                        // Display the cancel order modal
                        displayModal('cancelOrderModal');
                    },
                    error: function (xhr, status, error) {
                        // Handle any errors or display error message
                    }
                });
            });
        });
        
        trackOrderButtons.forEach(function (button) {
            button.addEventListener('click', function () {
                var orderproId = this.getAttribute('data-orderproid');
                
                // Send an AJAX request to the server to retrieve the shipment status
                $.ajax({
                    url: 'trackOrder',
                    type: 'GET',
                    data: { orderproId: orderproId },
                    success: function (response) {
                        // Update the track order modal with the shipment status
                        var shipmentStatus = response;
                        console.log(shipmentStatus);
                        updateShipmentStatus(shipmentStatus);
                        
                        // Open the track order modal
                        displayModal('trackOrderModal');
                    },
                    error: function (xhr, status, error) {
                        // Handle the error case
                        console.log(error);
                    }
                });
            });
        });
        
        function updateShipmentStatus(shipmentStatus) {
            // Reset all dots to the default color
            $('.dot').css('background-color', 'gray');
            
            // Determine the index of the current shipment status
            var statusIndex;
            switch (shipmentStatus) {
                case 'Order Placed':
                    statusIndex = 0;
                    break;
                case 'Order Processed':
                    statusIndex = 1;
                    break;
                case 'dispatched':
                    statusIndex = 2;
                    break;
                case 'Out for Delivery':
                    statusIndex = 3;
                    break;
                case 'Delivered':
                    statusIndex = 4;
                    break;
                default:
                    statusIndex = -1; // Handle the case when shipment status is not recognized
                    break;
            }
            
            // Update the color of the corresponding dot
            if (statusIndex >= 0) {
                $('.dot').eq(statusIndex).css('background-color', 'green');
            }
        }
        
        // Close the modal when the close button is clicked
        $('.close').click(function () {
            var modalId = $(this).closest('.modal').attr('id');
            closeModal(modalId);
        });
    });

        
        
   
