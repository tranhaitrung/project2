function selectFieldArticle(){
    var field = document.getElementById('select_article_search').value;
    if (field === "0" || field === "2" || field === "3" || field === "1"){
        document.getElementById('text-search-article').style.display = "block";
        document.getElementById('price-search-article').style.display = "none";
        document.getElementById('area-search-article').style.display = "none";
    }
    // diện tích
    if (field === "4"){
        document.getElementById('text-search-article').style.display = "none";
        document.getElementById('price-search-article').style.display = "none";
        document.getElementById('area-search-article').style.display = "block";
    }

    // Giá
    if (field === "5"){
        document.getElementById('text-search-article').style.display = "none";
        document.getElementById('price-search-article').style.display = "block";
        document.getElementById('area-search-article').style.display = "none";
    }
}