// ===== VALIDATION UTILITY =====
function showError(fieldId, message) {
    var field = document.getElementById(fieldId);
    var errorDiv = document.getElementById(fieldId + 'Error');
    
    if (!errorDiv) {
        errorDiv = document.createElement('div');
        errorDiv.id = fieldId + 'Error';
        errorDiv.className = 'invalid-feedback';
        field.parentNode.appendChild(errorDiv);
    }
    
    field.classList.add('is-invalid');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
}

function clearError(fieldId) {
    var field = document.getElementById(fieldId);
    var errorDiv = document.getElementById(fieldId + 'Error');
    
    if (field) {
        field.classList.remove('is-invalid');
        field.classList.add('is-valid');
    }
    
    if (errorDiv) {
        errorDiv.style.display = 'none';
    }
}

function clearAllErrors() {
    document.querySelectorAll('.is-invalid').forEach(function(el) {
        el.classList.remove('is-invalid');
    });
    document.querySelectorAll('.invalid-feedback').forEach(function(el) {
        el.style.display = 'none';
    });
}

function validateEmail(email) {
    var re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return re.test(email);
}

function validatePhone(phone) {
    var re = /^[0-9]{10,11}$/;
    return re.test(phone);
}

function validatePassword(password) {
    return password.length >= 6;
}

function validateUsername(username) {
    var re = /^[a-zA-Z0-9_]{3,30}$/;
    return re.test(username);
}