function filterProducts(category) {
    const products = document.querySelectorAll('.product-card');
    products.forEach(product => {
        if (product.getAttribute('data-category') === category || category === 'all') {
            product.style.display = 'block';
        } else {
            product.style.display = 'none';
        }
    });
}

// 페이지 로드 시 모든 제품을 표시
document.addEventListener('DOMContentLoaded', () => {
    filterProducts('all');
});