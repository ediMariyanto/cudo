package com.cudocom.barang.util;

public interface RecyclerviewOnClickListener {
    void onClick(String value);

    void onClickDialog(String value, Integer id);

    void onClickDetail(Integer value);

    void onClickSellId(Integer portofoliolistId, Integer reksadanaId, double unit, double withdraw, String lastpricedate);

    void onClickBuyId(Integer buyId);

    void onClickswitchId(Integer portofoliolistId, Integer reksadanaId, double unit, double withdraw,
                         double nilaiInvestasi, double keuntungan, double averageprice, double lastprice, double returnvalue);

    void onClickCompare(Integer position, Integer compareId, Integer event, String namePrdk);

    void onClickArticle(Integer id);
}
