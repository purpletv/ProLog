

function addToCart(productId) {
	
	  console.log("Add to cart called");

		  $.ajax({
    		    url: "addToCart",
    		    method: 'GET',
    		    data: { productId: productId },
    		    success: function(response) {
    		    	if(response.success)
    		    		{
    		    		var bt='<button class="removeFromCart" data-product-id="+ productId+">Remove from Cart</button>';

    		    		$(".addToCartButton").html(bt);
    		    		}
    		    },
    		    error: function(xhr, status, error) {
    		      console.log('AJAX Error: ' + error);
    		    }
    		  });
	}

  function addToWishlist(productId) {
	 
	  $.ajax({
	    url: "addToWishlist",
	    method: 'GET',
	    data: {productId: productId },
	    success: function(response) {
	      $('#display').html(response); // Set the response HTML as the inner HTML of the cart items element
	    },
	    error: function(xhr, status, error) {
	      console.log('AJAX Error: ' + error);
	    }
	  });
  }
  
  function removeFromCart(productId) {
	  console.log("Remove from cart called");
	  $.ajax({
	    url: "removeFromCart",
	    method: 'GET',
	    data: { productId: productId },
	    success: function(response) {
	      showCart(); // Set the response HTML as the inner HTML of the cart items element
	    },
	    error: function(xhr, status, error) {
	      console.log('AJAX Error: ' + error);
	    }
	  });
	}
  
  function removeFromWishlist(productId) {
	  console.log("Remove from wishlist called");
	  $.ajax({
	    url: "removeFromWishlist",
	    method: 'GET',
	    data: { productId: productId },
	    success: function(response) {
	      showCart(); // Set the response HTML as the inner HTML of the cart items element
	    },
	    error: function(xhr, status, error) {
	      console.log('AJAX Error: ' + error);
	    }
	  });
	}
  
  
  function showCart() {
	  console.log("show cart called");

	  $.ajax({
	    url: "cartItems",
	    method: 'GET',
	    data:{userId: 1},
	    success: function(response) {
	      $('#edit').html(response); // Set the response HTML as the inner HTML of the cart items element
	    },
	    error: function(xhr, status, error) {
	      console.log('AJAX Error: ' + error);
	    }
	  });
	}
  
  function showWishlist() {
	  $.ajax({
	    url: "wishlistItems",
	    method: 'GET',
	    data:{userId: 1},
	    success: function(response) {
	  	  console.log("profile wishlist");

	      $('#edit').html(response); 
	    },
	    error: function(xhr, status, error) {
	      console.log('AJAX Error: ' + error);
	    }
	  });
  }
  function buynow()
  {
	  console.log("buy now");
	        var notAvailable = $(".not-available");
	        if (notAvailable.length > 0) {
	            alert("Please check the availability of all products before placing the order!");
	        }   
	        else{
	  	window.location.href="buycartitems";  
	        }
	    }
	
$(document).ready(function() {
	$(document).on('click', '.buyid', function(event) {
		    event.preventDefault();
buynow()
});
	  
	  $(document).on('click', '.addToCartButton', function(event) {
	    event.preventDefault();
	    var productId = $(this).data('product-id');
	    console.log(productId);
	    addToCart(productId);
	  });
	  
	  $(document).on('click', '.removeFromCart', function(event) {
	    event.preventDefault();
	    var productId = $(this).data('product-id');
	    console.log(productId);
	    removeFromCart(productId);
	  });
	  
	  $(document).on('click', '.addToWishlistButton', function(event) {
		    event.preventDefault();
		    var productId = $(this).data('product-id');
		    console.log(productId);
		    addToWishlist(productId);
		});
	  
	  $(document).on('click', '.removeFromWishlist', function(event) {
	    event.preventDefault();
	    var productId = $(this).data('product-id');
	    console.log(productId);
	    removeFromWishlist(productId);
	  });
	  

	  $('#cart-button').click(function() {
	      showCart();
	  });
	  $('#Wishlist-button').click(function() {
	      showWishlist();
	  });
});

function editprofile() {
    var ele = document.getElementById("edit");
    ele.innerHTML = `
    <form action="updateProfile" method="post">    
        <label>CustomerId:</label>
        <input type="text" id="custId" name="custId" value="${cust != null ? cust.custId : ""}" readonly><br>

        <label for="custName">Name:</label>
        <input type="text" id="custName" name="custName" value="${cust != null ? cust.custName : ""}"><br>

        <label for="custMobile">Mobile:</label>
        <input type="text" id="custMobile" name="custMobile" value="${cust != null ? cust.custMobile : ""}"><br>

        <label for="custLocation">Location:</label>
        <input type="text" id="custLocation" name="custLocation" value="${cust != null ? cust.custLocation : ""}"><br>

        <label for="custAddress">Address:</label>
        <input type="text" id="custAddress" name="custAddress" value="${cust != null ? cust.custAddress : ""}"><br>
        
        <label for="custPincode">Pincode:</label>
        <input type="text" id="custPincode" name="custPincode" value="${cust != null ? cust.custPincode : ""}"><br>

        <label for="custSAddress">Shipping Address:</label>
        <input type="text" id="custSAddress" name="custSAddress" value="${cust != null ? cust.custSAddress : ""}"><br>

        <label for="custsPincode">Shipment Pincode:</label>
        <input type="text" id="custsPincode" name="custSpincode" value="${cust != null ? cust.custSpincode : ""}"><br>

        <input type="submit" value="Update Profile">
    </form>`;
}
