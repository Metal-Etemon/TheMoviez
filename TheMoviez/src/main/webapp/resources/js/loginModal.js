const body = document.querySelector('body');
const modal = document.querySelector('.modal');
const btnOpenPopup = document.querySelector('.btn-open-popup');
      
const close = document.querySelector(".modal-closeBtn");
      
      
btnOpenPopup.addEventListener('click', () => {
		modal.classList.toggle('show');

        if (modal.classList.contains('show')) {
          body.style.overflow = 'hidden';
        }
	});

modal.addEventListener('click', (event) => {
        if (event.target === modal) {
          modal.classList.toggle('show');

          if (!modal.classList.contains('show')) {
            body.style.overflow = 'auto';
          }
        }
	});
      
      
      
function init() {
	btnOpenPopup.addEventListener("click", function () {
	modal.classList.remove("hidden");
	});
	close.addEventListener("click", function () {
	modal.classList.add("hidden");
	})
	}

init();