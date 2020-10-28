package com.atjiumi.demo;

import com.alibaba.fastjson.JSONObject;
import com.atjiumi.es.entity.BcoDeclarationStatistics;
import com.atjiumi.es.mapper.BcoDeclarationStatisticsMapper;
import net.sf.json.JSONArray;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestData {

    //将object类型转换成BigDecimal
    public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger ) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }


    public  void jsondata() {
        Map<String ,String> map =new HashMap<>();
        map.put("Content-type", "application/json");
        map.put("accessToken","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjoiW1wi5rOo5YaM5YWs5Y-4XCIsXCLlhazlj7jnsbvlnovlkK_nlKhcIixcIuiOt-WPluaUtuS7tuS6uuaVsOaNrlwiLFwi5L2c5bqf55Sz5oql6KaB57SgMlwiLFwi5Y-W5raI6K6i5Y2VXCIsXCLorqLljZXlj5HpgIHnvJbovpFcIixcIuS_ruaUueaUtuasvuWNleeKtuaAgVwiLFwi6YeN5paw5Y-R6YCB6L-Q5Y2VXCIsXCLlr7zlh7rovb3otKfku5PljZVcIixcIuS_ruaUueWHuuWPo-a4heWNleaKpemAgeexu-Wei1wiLFwi5L-u5pS55riF5Y2V5oC75YiG5oql6YCB57G75Z6LXCIsXCLnvJbovpHov5DmirXkv6Hmga9cIixcIua3u-WKoOWFrOWPuFwiLFwi5YWs5Y-457G75Z6L56aB55SoXCIsXCLnvJbovpHnlLPmiqXopoHntKAyXCIsXCLlr7zlh7rpgInkuK3nmoTorqLljZXmlbDmja5cIixcIuWvvOWHuuaJgOacieagueaNruadoeS7tuafpeivoueahOiuouWNleWPkemAgeaVsOaNrlwiLFwi5a-85Ye66YCJ5Lit55qE5pS25qy-5Y2V5pWw5o2uXCIsXCLkv67mlLnov5DljZXnirbmgIFcIixcIuWvvOWHuuijhei9veWNlVwiLFwi5a-85YWl6L-Q5oq15pWw5o2uXCIsXCLkv67mlLnnprvlooPljZXlpITnkIbnirbmgIFcIixcIuWFrOWPuOS_oeaBr-WuoeaguFwiLFwi5YWs5Y-457G75Z6L5Yig6ZmkXCIsXCLlr7zlhaXnlLPmiqXopoHntKDmlbDmja4yXCIsXCLlr7zlh7rmiYDmnInmoLnmja7mnaHku7bmn6Xor6LnmoTorqLljZXmlbDmja5cIixcIuS_ruaUueiuouWNleeUs-aKpeexu-Wei1wiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE5pS25qy-5Y2V5pWw5o2uXCIsXCLnvJbovpHov5DljZVcIixcIuWvvOWHuuS8geS4mueUs-aKpeaVsOaNrue7n-iuoVwiLFwi5L-u5pS55oC75YiG5aSE55CG54q25oCBXCIsXCLlj5HpgIHmgLvliIbljZVcIixcIuWvvOWHuumAieS4reeahOi_kOaKteaVsOaNrlwiLFwi57yW6L6R56a75aKD5Y2V5L-h5oGvXCIsXCLnvJbovpHlhazlj7jkv6Hmga9cIixcIuW8uuWItuWIt-aWsOeUs-aKpeimgee0oEVT5pWw5o2uMlwiLFwi6I635Y-W55So5oi3IEV4Y2VsIOiuouWNleWfuuehgOmFjee9rlwiLFwi5L-u5pS55pS25qy-5Y2V55Sz5oql57G75Z6LXCIsXCLlr7zlh7rpgInkuK3nmoTov5DljZXmlbDmja5cIixcIuaPkOi_kOWNleaooeeJiOS4i-i9vVwiLFwi6L-Q5oq156Gu6K6k5Y2V5LiL6L29XCIsXCLkv67mlLnmuIXljZXnirbmgIFcIixcIuWPkemAgea4heWNlVwiLFwi5om56YeP5re75Yqg5oC75YiG5L-h5oGvXCIsXCLlr7zlh7rmiYDmnInmoLnmja7mnaHku7bmn6Xor6LnmoTov5DmirXkv6Hmga9cIixcIuWvvOWHuumAieS4reeahOemu-Wig-S_oeaBr-aVsOaNrlwiLFwi5qC55o2u5o-Q6L-Q5Y2V5Y-35Yig6Zmk5pWw5o2uXCIsXCLliKDpmaTlhazlj7jkv6Hmga9cIixcIua3u-WKoOWNj-iurlwiLFwi6aKE5b2V5YWl5a-85YWlZXhjZWzmnYPpmZBcIixcIuiuouWNleWPkemAgVwiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE6L-Q5Y2V5pWw5o2uXCIsXCLmt7vliqDmj5Dov5DljZVcIixcIui_kOaKteehruiupOWNleaVsOaNruiOt-WPllwiLFwi5pKk6ZSA5riF5Y2VXCIsXCLph43mlrDlj5HpgIHmuIXljZVcIixcIue8lui-keaAu-WIhuWNleS_oeaBr1wiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE56a75aKD5L-h5oGv5pWw5o2uXCIsXCLlkK_nlKjlhazlj7hcIixcIuiuvuS4uum7mOiupFwiLFwi6I635Y-W6aKE5b2V5YWl6K6i5Y2V5pWw5o2uXCIsXCLorqLljZXph43mlrDlj5HpgIFcIixcIuWPkemAgeaUtuasvuWNlVwiLFwi5L-u5pS56L-Q5Y2V55Sz5oql57G75Z6LXCIsXCLnvJbovpHmj5Dov5DljZVcIixcIuWkhOeQhuWvvOWFpemUmeivr-aVsOaNrlwiLFwi5a-85Ye66YCJ5Lit55qE5riF5Y2V5pWw5o2uXCIsXCLkv67mlLnmj5Dov5DljZXkuIvmiYDmnInorqLljZXkv6Hmga_lh7rlj6Plj6Plsrjku6PnoIFcIixcIuWvvOWFpea4heWNleaAu-WIhuaVsOaNrlwiLFwi5Y-R6YCB6L-Q5oq15Y2VXCIsXCLkv67mlLnnprvlooPmiqXpgIHnsbvlnotcIixcIuemgeeUqOWFrOWPuFwiLFwi6I635Y-W5pWP5oSf44CB6buR5ZCN5Y2V6K-N5pWw5o2uXCIsXCLorqLljZXmqKHniYjkuIvovb1cIixcIuS_ruaUueiuouWNleeKtuaAgVwiLFwi6YeN5paw5Y-R6YCB5pS25qy-5Y2VXCIsXCLliKDpmaTmj5Dov5DljZVcIixcIuWkhOeQhui_kOaKteWbnuaJp-eKtuaAgVwiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE5riF5Y2V5pWw5o2uXCIsXCLlr7zlh7rpgInkuK3nmoTmuIXljZXmgLvliIbmlbDmja5cIixcIua3u-WKoOeUs-aKpeimgee0oDJcIixcIue8lui-keiuouWNlVwiLFwi5a-85Ye66YCJ5Lit55qE6K6i5Y2V5Y-R6YCB5pWw5o2uXCIsXCLlj5HpgIHov5DljZVcIixcIuS_ruaUueaPkOi_kOWNleeKtuaAgVwiLFwi57yW6L6R5riF5Y2V5piO57uG5Z-656GA5pWw5o2uXCIsXCLlkITljZXor4HnirbmgIHjgJDlpITnkIbjgIHlm57miafjgJHnirbmgIHnu5_orqFcIixcIuWvvOWHuuaJgOacieagueaNruadoeS7tuafpeivoueahOa4heWNleaAu-WIhuaVsOaNrlwiLFwi5L-u5pS56L-Q5oq154q25oCBXCIsXCLlj5HpgIHnprvlooPljZVcIixcIua3u-WKoOeUqOaIt1wiLFwi57yW6L6R55So5oi3XCIsXCLnpoHnlKjnlKjmiLdcIixcIuWQr-eUqOeUqOaIt1wiLFwi5Yig6Zmk55So5oi3XCIsXCLkuIrkvKDlm77niYdcIixcIuWvvOWFpeeUqOaIt1wiLFwi5re75Yqg6YOo6ZeoXCIsXCLmt7vliqDop5LoibJcIixcIue8lui-kemDqOmXqFwiLFwi57yW6L6R6KeS6ImyXCIsXCLliKDpmaTpg6jpl6hcIixcIuWIoOmZpOinkuiJslwiLFwi5YiG6YWN5p2D6ZmQXCIsXCLmt7vliqDoj5zljZVcIixcIue8lui-keiPnOWNlVwiLFwi5Yig6Zmk6I-c5Y2VXCIsXCLph43mlrDlj5HpgIHnprvlooPljZVcIixcIua3u-WKoOaUtuS7tuS6ulwiLFwi5YmU6Zmk5pWw5o2uXCIsXCLorqLljZVFeGNlbOWvvOWFpVwiLFwi6YeN5paw5Y-R6YCB5oC75YiG5Y2VXCIsXCLmoLnmja7mj5Dov5DljZXlj7csIOS_ruaUuei_kOi-k-W3peWFt-S_oeaBr1wiLFwi57yW6L6R6aKE5b2V5YWl5Li76KGo5pWw5o2uXCIsXCLph43mlrDlj5HpgIHov5DmirXljZVcIixcIuaPkOi_kOWNleWvvOWFpeS4i-i9vVwiLFwi5re75Yqg5a6a5pe25Lu75YqhXCIsXCLnvJbovpHlrprml7bku7vliqFcIixcIuaaguWBnOWumuaXtuS7u-WKoVwiLFwi5oGi5aSN5a6a5pe25Lu75YqhXCIsXCLliKDpmaTlrprml7bku7vliqFcIixcIuaPkOi_kOWNleWvvOWFpVwiLFwi57yW6L6R5pS25Lu25Lq6XCIsXCLorqLljZXpgJrnlKjkuIvovb1cIixcIuWIoOmZpOaAu-WIhuWNlVwiLFwi5L-u5pS55Y2V6K-B5YWs5Y-45Y2P6K6uXCIsXCLmt7vliqDmlY_mhJ_jgIHpu5HlkI3ljZXor41cIixcIuWIoOmZpOmihOW9leWFpeS4u-ihqOaVsOaNrlwiLFwi57yW6L6R5pWP5oSf44CB6buR5ZCN5Y2V6K-NXCIsXCLlr7zlhaXorqLljZXpooTlvZXlhaXmlbDmja5cIixcIuWIoOmZpOaUtuS7tuS6ulwiLFwi5L-u5pS5IOaIliDliZTpmaTmlbDmja5cIixcIuaVj-aEn-OAgem7keWQjeWNleivjeaooeadv-S4i-i9vVwiLFwi5LiL6L296K6i5Y2V6aKE5b2V5YWl5pWw5o2uXCIsXCLmlLbku7bkurrlr7zlhaXmqKHmnb_kuIvovb1cIixcIuWvvOWFpeaVj-aEn-OAgem7keWQjeWNleivjeaVsOaNrlwiLFwi6I635Y-W6aKE5b2V5YWl6K6i5Y2V5piO57uG6KGo5pWw5o2uXCIsXCLlr7zlhaXmlLbku7bkurrmlbDmja5cIixcIuagueaNrueKtuaAge-8jOiOt-WPluWkhOeQhuWksei0peeahOaVsOaNrlwiLFwi5aSE55CG5ZCr5pyJ5pWP5oSf6K-N44CB6buR5ZCN5Y2V6K-N55qE5pWw5o2uXCIsXCLlpITnkIbljLnphY3lpLHotKXnmoTmlbDmja5cIixcIuabv-aNoueUs-aKpeimgee0oEhT57yW56CBXCIsXCLnvJbovpHorqLljZXmmI7nu4booajmlbDmja5cIixcIuS_ruaUueW3suaPkOS6pOeahOabv-aNouivjVwiLFwiUk9MRV9BRE1JTlwiXSIsImV4cCI6MTYwMjkxNzQ4MX0.izXXtn3J2rz9_XkwCOUyot1Et4W8wAGjSYOrBxIwPyQTcNZ2ampzhsAOcn-8jueSkyhJ--yIYMaolOYlbfgViQ");
        String url ="http://185r217e29.qicp.vip/airy/apply/statistics/getStatisticsData";
        JSONObject body=new JSONObject();
        body.put("accessToken", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjoiW1wi5rOo5YaM5YWs5Y-4XCIsXCLlhazlj7jnsbvlnovlkK_nlKhcIixcIuiOt-WPluaUtuS7tuS6uuaVsOaNrlwiLFwi5L2c5bqf55Sz5oql6KaB57SgMlwiLFwi5Y-W5raI6K6i5Y2VXCIsXCLorqLljZXlj5HpgIHnvJbovpFcIixcIuS_ruaUueaUtuasvuWNleeKtuaAgVwiLFwi6YeN5paw5Y-R6YCB6L-Q5Y2VXCIsXCLlr7zlh7rovb3otKfku5PljZVcIixcIuS_ruaUueWHuuWPo-a4heWNleaKpemAgeexu-Wei1wiLFwi5L-u5pS55riF5Y2V5oC75YiG5oql6YCB57G75Z6LXCIsXCLnvJbovpHov5DmirXkv6Hmga9cIixcIua3u-WKoOWFrOWPuFwiLFwi5YWs5Y-457G75Z6L56aB55SoXCIsXCLnvJbovpHnlLPmiqXopoHntKAyXCIsXCLlr7zlh7rpgInkuK3nmoTorqLljZXmlbDmja5cIixcIuWvvOWHuuaJgOacieagueaNruadoeS7tuafpeivoueahOiuouWNleWPkemAgeaVsOaNrlwiLFwi5a-85Ye66YCJ5Lit55qE5pS25qy-5Y2V5pWw5o2uXCIsXCLkv67mlLnov5DljZXnirbmgIFcIixcIuWvvOWHuuijhei9veWNlVwiLFwi5a-85YWl6L-Q5oq15pWw5o2uXCIsXCLkv67mlLnnprvlooPljZXlpITnkIbnirbmgIFcIixcIuWFrOWPuOS_oeaBr-WuoeaguFwiLFwi5YWs5Y-457G75Z6L5Yig6ZmkXCIsXCLlr7zlhaXnlLPmiqXopoHntKDmlbDmja4yXCIsXCLlr7zlh7rmiYDmnInmoLnmja7mnaHku7bmn6Xor6LnmoTorqLljZXmlbDmja5cIixcIuS_ruaUueiuouWNleeUs-aKpeexu-Wei1wiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE5pS25qy-5Y2V5pWw5o2uXCIsXCLnvJbovpHov5DljZVcIixcIuWvvOWHuuS8geS4mueUs-aKpeaVsOaNrue7n-iuoVwiLFwi5L-u5pS55oC75YiG5aSE55CG54q25oCBXCIsXCLlj5HpgIHmgLvliIbljZVcIixcIuWvvOWHuumAieS4reeahOi_kOaKteaVsOaNrlwiLFwi57yW6L6R56a75aKD5Y2V5L-h5oGvXCIsXCLnvJbovpHlhazlj7jkv6Hmga9cIixcIuW8uuWItuWIt-aWsOeUs-aKpeimgee0oEVT5pWw5o2uMlwiLFwi6I635Y-W55So5oi3IEV4Y2VsIOiuouWNleWfuuehgOmFjee9rlwiLFwi5L-u5pS55pS25qy-5Y2V55Sz5oql57G75Z6LXCIsXCLlr7zlh7rpgInkuK3nmoTov5DljZXmlbDmja5cIixcIuaPkOi_kOWNleaooeeJiOS4i-i9vVwiLFwi6L-Q5oq156Gu6K6k5Y2V5LiL6L29XCIsXCLkv67mlLnmuIXljZXnirbmgIFcIixcIuWPkemAgea4heWNlVwiLFwi5om56YeP5re75Yqg5oC75YiG5L-h5oGvXCIsXCLlr7zlh7rmiYDmnInmoLnmja7mnaHku7bmn6Xor6LnmoTov5DmirXkv6Hmga9cIixcIuWvvOWHuumAieS4reeahOemu-Wig-S_oeaBr-aVsOaNrlwiLFwi5qC55o2u5o-Q6L-Q5Y2V5Y-35Yig6Zmk5pWw5o2uXCIsXCLliKDpmaTlhazlj7jkv6Hmga9cIixcIua3u-WKoOWNj-iurlwiLFwi6aKE5b2V5YWl5a-85YWlZXhjZWzmnYPpmZBcIixcIuiuouWNleWPkemAgVwiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE6L-Q5Y2V5pWw5o2uXCIsXCLmt7vliqDmj5Dov5DljZVcIixcIui_kOaKteehruiupOWNleaVsOaNruiOt-WPllwiLFwi5pKk6ZSA5riF5Y2VXCIsXCLph43mlrDlj5HpgIHmuIXljZVcIixcIue8lui-keaAu-WIhuWNleS_oeaBr1wiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE56a75aKD5L-h5oGv5pWw5o2uXCIsXCLlkK_nlKjlhazlj7hcIixcIuiuvuS4uum7mOiupFwiLFwi6I635Y-W6aKE5b2V5YWl6K6i5Y2V5pWw5o2uXCIsXCLorqLljZXph43mlrDlj5HpgIFcIixcIuWPkemAgeaUtuasvuWNlVwiLFwi5L-u5pS56L-Q5Y2V55Sz5oql57G75Z6LXCIsXCLnvJbovpHmj5Dov5DljZVcIixcIuWkhOeQhuWvvOWFpemUmeivr-aVsOaNrlwiLFwi5a-85Ye66YCJ5Lit55qE5riF5Y2V5pWw5o2uXCIsXCLkv67mlLnmj5Dov5DljZXkuIvmiYDmnInorqLljZXkv6Hmga_lh7rlj6Plj6Plsrjku6PnoIFcIixcIuWvvOWFpea4heWNleaAu-WIhuaVsOaNrlwiLFwi5Y-R6YCB6L-Q5oq15Y2VXCIsXCLkv67mlLnnprvlooPmiqXpgIHnsbvlnotcIixcIuemgeeUqOWFrOWPuFwiLFwi6I635Y-W5pWP5oSf44CB6buR5ZCN5Y2V6K-N5pWw5o2uXCIsXCLorqLljZXmqKHniYjkuIvovb1cIixcIuS_ruaUueiuouWNleeKtuaAgVwiLFwi6YeN5paw5Y-R6YCB5pS25qy-5Y2VXCIsXCLliKDpmaTmj5Dov5DljZVcIixcIuWkhOeQhui_kOaKteWbnuaJp-eKtuaAgVwiLFwi5a-85Ye65omA5pyJ5qC55o2u5p2h5Lu25p-l6K-i55qE5riF5Y2V5pWw5o2uXCIsXCLlr7zlh7rpgInkuK3nmoTmuIXljZXmgLvliIbmlbDmja5cIixcIua3u-WKoOeUs-aKpeimgee0oDJcIixcIue8lui-keiuouWNlVwiLFwi5a-85Ye66YCJ5Lit55qE6K6i5Y2V5Y-R6YCB5pWw5o2uXCIsXCLlj5HpgIHov5DljZVcIixcIuS_ruaUueaPkOi_kOWNleeKtuaAgVwiLFwi57yW6L6R5riF5Y2V5piO57uG5Z-656GA5pWw5o2uXCIsXCLlkITljZXor4HnirbmgIHjgJDlpITnkIbjgIHlm57miafjgJHnirbmgIHnu5_orqFcIixcIuWvvOWHuuaJgOacieagueaNruadoeS7tuafpeivoueahOa4heWNleaAu-WIhuaVsOaNrlwiLFwi5L-u5pS56L-Q5oq154q25oCBXCIsXCLlj5HpgIHnprvlooPljZVcIixcIua3u-WKoOeUqOaIt1wiLFwi57yW6L6R55So5oi3XCIsXCLnpoHnlKjnlKjmiLdcIixcIuWQr-eUqOeUqOaIt1wiLFwi5Yig6Zmk55So5oi3XCIsXCLkuIrkvKDlm77niYdcIixcIuWvvOWFpeeUqOaIt1wiLFwi5re75Yqg6YOo6ZeoXCIsXCLmt7vliqDop5LoibJcIixcIue8lui-kemDqOmXqFwiLFwi57yW6L6R6KeS6ImyXCIsXCLliKDpmaTpg6jpl6hcIixcIuWIoOmZpOinkuiJslwiLFwi5YiG6YWN5p2D6ZmQXCIsXCLmt7vliqDoj5zljZVcIixcIue8lui-keiPnOWNlVwiLFwi5Yig6Zmk6I-c5Y2VXCIsXCLph43mlrDlj5HpgIHnprvlooPljZVcIixcIua3u-WKoOaUtuS7tuS6ulwiLFwi5YmU6Zmk5pWw5o2uXCIsXCLorqLljZVFeGNlbOWvvOWFpVwiLFwi6YeN5paw5Y-R6YCB5oC75YiG5Y2VXCIsXCLmoLnmja7mj5Dov5DljZXlj7csIOS_ruaUuei_kOi-k-W3peWFt-S_oeaBr1wiLFwi57yW6L6R6aKE5b2V5YWl5Li76KGo5pWw5o2uXCIsXCLph43mlrDlj5HpgIHov5DmirXljZVcIixcIuaPkOi_kOWNleWvvOWFpeS4i-i9vVwiLFwi5re75Yqg5a6a5pe25Lu75YqhXCIsXCLnvJbovpHlrprml7bku7vliqFcIixcIuaaguWBnOWumuaXtuS7u-WKoVwiLFwi5oGi5aSN5a6a5pe25Lu75YqhXCIsXCLliKDpmaTlrprml7bku7vliqFcIixcIuaPkOi_kOWNleWvvOWFpVwiLFwi57yW6L6R5pS25Lu25Lq6XCIsXCLorqLljZXpgJrnlKjkuIvovb1cIixcIuWIoOmZpOaAu-WIhuWNlVwiLFwi5L-u5pS55Y2V6K-B5YWs5Y-45Y2P6K6uXCIsXCLmt7vliqDmlY_mhJ_jgIHpu5HlkI3ljZXor41cIixcIuWIoOmZpOmihOW9leWFpeS4u-ihqOaVsOaNrlwiLFwi57yW6L6R5pWP5oSf44CB6buR5ZCN5Y2V6K-NXCIsXCLlr7zlhaXorqLljZXpooTlvZXlhaXmlbDmja5cIixcIuWIoOmZpOaUtuS7tuS6ulwiLFwi5L-u5pS5IOaIliDliZTpmaTmlbDmja5cIixcIuaVj-aEn-OAgem7keWQjeWNleivjeaooeadv-S4i-i9vVwiLFwi5LiL6L296K6i5Y2V6aKE5b2V5YWl5pWw5o2uXCIsXCLmlLbku7bkurrlr7zlhaXmqKHmnb_kuIvovb1cIixcIuWvvOWFpeaVj-aEn-OAgem7keWQjeWNleivjeaVsOaNrlwiLFwi6I635Y-W6aKE5b2V5YWl6K6i5Y2V5piO57uG6KGo5pWw5o2uXCIsXCLlr7zlhaXmlLbku7bkurrmlbDmja5cIixcIuagueaNrueKtuaAge-8jOiOt-WPluWkhOeQhuWksei0peeahOaVsOaNrlwiLFwi5aSE55CG5ZCr5pyJ5pWP5oSf6K-N44CB6buR5ZCN5Y2V6K-N55qE5pWw5o2uXCIsXCLlpITnkIbljLnphY3lpLHotKXnmoTmlbDmja5cIixcIuabv-aNoueUs-aKpeimgee0oEhT57yW56CBXCIsXCLnvJbovpHorqLljZXmmI7nu4booajmlbDmja5cIixcIuS_ruaUueW3suaPkOS6pOeahOabv-aNouivjVwiLFwiUk9MRV9BRE1JTlwiXSIsImV4cCI6MTYwMjkxNzQ4MX0.izXXtn3J2rz9_XkwCOUyot1Et4W8wAGjSYOrBxIwPyQTcNZ2ampzhsAOcn-8jueSkyhJ--yIYMaolOYlbfgViQ");
        String s = httpPostWithJsonAndHeader(url,body.toJSONString(),map);
        //转成json
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(s);
        //获取里面数组的内容
        JSONArray result = jsonObject.getJSONArray("result");
        net.sf.json.JSONObject row =null;

        //定义集合将对象存入集合中
        List<BcoDeclarationStatistics> bdslist =new ArrayList<>();

        //遍历数组，将值全部拿出
        for (int i =0;i<result.size();i++){
            row = result.getJSONObject(i);
            BcoDeclarationStatistics bds =new BcoDeclarationStatistics();
           bds.setCreateTime((String) row.get("createTime"));
           bds.setUpdateTime((String) row.get("updateTime"));
           bds.setId((String) row.get("id"));
           bds.setEbcCode((String) row.get("ebcCode"));
           bds.setEbcName((String) row.get("ebcName"));
           bds.setLogisticsCode((String) row.get("logisticsCode"));
           bds.setLogisticsName((String) row.get("logisticsName"));
           bds.setStatisticsFlag((String) row.get("statisticsFlag"));
           bds.setCountry((String) row.get("country"));
           bds.setPod((String) row.get("pod"));
           bds.setBillNo((String) row.get("billNo"));
           bds.setLogisticsTotal((Integer) row.get("logisticsTotal"));
           bds.setTotalPackNo((Integer) row.get("totalPackNo"));
           bds.setTotalGoodsValue(getBigDecimal(row.get("totalGoodsValue")));
           bds.setTotalGrossWeight(getBigDecimal(row.get("totalGrossWeight")));
           bds.setCustomsCode((String) row.get("customsCode"));
           bds.setCurrency((String) row.get("currency"));
           bds.setPortCode((String) row.get("portCode"));
           bds.setIeFlag((String) row.get("ieFlag"));
           bds.setIeDate((String) row.get("ieDate"));
           bds.setTrafMode((String) row.get("trafMode"));
           bds.setDomesticTrafNo((String) row.get("domesticTrafNo"));
           bds.setGoodsInfo((String) row.get("goodsInfo"));
           bds.setVoyageNo((String) row.get("voyageNo"));
           bds.setStatus((Integer) row.get("status"));
           bdslist.add(bds);
        }
        for (BcoDeclarationStatistics b :bdslist){
            System.out.println(b);
        }

    }


    public static String httpPostWithJsonAndHeader(String url, String json, Map<String, String> headsMap) {
        String result = "";
        System.out.println("本次请求地址:{} "+url);
        System.out.println("本次传递数据:{} "+json);
        HttpPost httpPost = new HttpPost(url);
        System.out.println("是null吗+"+httpPost);
        StringEntity entity = new StringEntity(json, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        //头
        if (headsMap != null && !headsMap.isEmpty()) {
            headsMap.forEach((key, value) -> {
                httpPost.addHeader(key, value);
            });
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("HTTP请求成功");
                // 从响应模型中获取响应实体
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    result = EntityUtils.toString(responseEntity);
                }
            } else {
                System.out.println("HTTP请求失败");
                return null;
            }
            System.out.println("返回的结果"+result);
            return result;
        } catch (Exception e) {
            System.out.println("http请求出现异常"+e);
            return null;
        }
    }

}

