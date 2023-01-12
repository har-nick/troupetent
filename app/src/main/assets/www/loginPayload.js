const observer = new MutationObserver((mutations) => {
	mutations.forEach((mutation) => {
		console.log(mutation.target.textContent)
//	ELIPSES ARE U+2026. NOT U+002E * 3
		if (mutation.target.textContent == 'Please wait…') {
			TroupetentLoginInterface.onLoginSubmit()
    }
    else if (mutation.target.classList.contains('alert')) {
    	console.log("Troupetent: " + mutation.target.textContent)
    }
    else {
			TroupetentLoginInterface.onCaptchaServed()
    }
  });
});

// Submit button contains two <div> children
// The second only has text content on form submission. We can use this as a notifier.
const submitButton = document.querySelector('div[data-bind="if: submitting"]')
observer.observe(submitButton, {childList: true, subtree: true})

// Monitor reCaptcha parent element to display WebView if served
const captchaParent = document.body.lastChild
observer.observe(captchaParent, {attributes: true, childList: true, subtree: true})

// Alert boxes are empty until errors are displayed.
// We monitor them to pass to the UI.
const alerts = document.querySelectorAll('.alert');
observer.observe(alerts[1], {attributes: true});
observer.observe(alerts[2], {attributes: true});