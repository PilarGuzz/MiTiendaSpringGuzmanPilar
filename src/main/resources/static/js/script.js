/**
 * 
 */
 
 function logOut() {
		const formLogout = document.getElementById("logoutForm");

		formLogout.submit();
	}
	
function changeRole() {
	const changeRole = document.getElementById("changeRole");

	changeRole.addEventListener('click', (e)=>{
			
	})
}
	
(function() {
 	const params = new Proxy(new URLSearchParams(window.location.search), {
  		get: (searchParams, prop) => searchParams.get(prop),
	});
// Get the value of "some_key" in eg "https://example.com/?some_key=some_value"
	let value = params.view;
	if(value == 'USER'){
		let divsToHide = document.getElementsByClassName("adminOnly");
	}
})();