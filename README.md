Barnehagen
==========

REST API update
---------------

### Routes ###

* 	GET     /children/ 
	Returns all childern                
* 	GET     /children/:id   
	Returns a single child with spesificed ID           
* 	GET     /children/new/:time 
	Returns all childern added or changed since time         
* 	GET     /children/find/:name 
	Returns all children matching name      
* 	POST    /children/ 
	Persists new child from JSON object in HTTP data content                 
* 	PUT     /children/:id 
	Updates child with id from JSON object in HTTP data content            
* 	DELETE  /children/:id 
	Deletes child with id

###  JSON Object ###

	{
	  "name":"Ola Normann Junior",
	  "guardians": [
	    {
	      "name":"Ola Normann",
	      "phone":"01234567",
	      "address":
	      {
	        "street":"NorgesGaten 4",
	        "postal":"0123",
	        "postalArea":"Byen"
	      }
	    }
	  ]
	}      
