function editCart(cartItemId, buyCount) {
    window.location.href = 'cart.do?operate=editCart&cartItemId=' + cartItemId + '&buyCount=' + buyCount;
}

window.onload = function () {
    var vue = new Vue({
        el: "#cart_div",
        data: {
            cart:{}
        },
        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "cartInfo"
                    }
                })
                    .then(function (value) {
                        var cart = value.data;
                        vue.cart = cart;
                    })
                    .catch(function (reason) {
                    });
            },
            editCart:function (cartItemId, buyCount) {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "editCart1",
                        cartItemId:cartItemId,
                        buyCount:buyCount
                    }
                })
                    .then(function (value) {
                        var cart = value.data;
                        vue.cart = cart;
                    })
                    .catch(function (reason) {
                    });
            }
        },
        "beforeMount": function () {
            this.getCart();
        }
    });

}