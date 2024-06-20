$(function () {
    // shopping basket recomend_product_slider
    let swiper = new Swiper(".recomendSlider", {
        slidesPerView: 5,
        spaceBetween: 30,
        clickable: true,
        slidesPerGroup: 5,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        breakpoints: {
            960: {
                slidesPerView: 5,
                slidesPerGroup: 5,
            },
            640: {
                slidesPerView: 3.2,
                slidesPerGroup: 3.2,
            },
            475: {
                slidesPerView: 2.2,
                slidesPerGroup: 2.2,
            },
            0: {
                slidesPerView: 1.2,
                slidesPerGroup: 1.2,
            }
        }
    });


    // 전체 삭제 클릭 시 삭제
    $('.delete_all').click(function () {
        $('.item_list').css("display", 'none');
    })

    // item wrap close_btn click event
    $('.close_btn').click(function () {
        $(this).parents('.item_list').css("display", 'none');
    })
})