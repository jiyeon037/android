package com.example.lklab.xmltest;

import static com.example.lklab.xmltest.XMLUtil.*;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    EditText textBox;
    Button button;
    ListView listView;
    StockAdapter adapter;
    String xml;
    ArrayList<Stock> stocksList = new ArrayList<>();

    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String METHOD_NAME = "GetStockInfo";
    private static final String SOAP_ACTION =
            "http://tempuri.org/StockInterface/GetStockInfo";
    private static final String URL = "http://211.233.61.250:8810/WCFAppLogic8810/Erp.BusinessManager.StockService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        textBox = (EditText) findViewById(R.id.textBox);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    xml = new CallWebService().execute(textBox.getText().toString()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                stocksList = MakeStockObjects(xml);
                addData();
            }
        });
        adapter = new StockAdapter(this, R.layout.item_list, stocksList);
        listView.setAdapter(adapter);
        // addData();
    }

    private void addData() {
        int index = 0;
        for(Stock stock : stocksList){
            adapter.add(stocksList.get(index));
            index++;
        }
    }

    private ArrayList<Stock> MakeStockObjects(String xml) {

        List<List<String>> list = new ArrayList<>();

        list.add(0, GetElementValue("stockNum", xml)); // tagName의 list(30개)를 List로 반환
        list.add(1, GetElementValue("StockPartCodeX", xml));
        list.add(2, GetElementValue("BundleItems", xml));
        list.add(3, GetElementValue("BundleNum", xml));
        list.add(4, GetElementValue("BundleRemainder", xml));
        list.add(5, GetElementValue("StockQty", xml));

        Stock[] stock = new Stock[30];

        // stock 객체 30개 생성
        for (int i = 0; i < 30; i++) {
            int j = 0;
            stock[i] = MakeStockInstance(list.get(j).get(i), list.get(j + 1).get(i), list.get(j + 2).get(i),
                    list.get(j + 3).get(i), list.get(j + 4).get(i), list.get(j + 5).get(i));
        }

        // Add the Stock objects to an ArrayList
        ArrayList<Stock> stockList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            stockList.add(stock[i]);
        }
        return stockList;
    }

    class CallWebService extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String xml = "";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.setName("stockType");
            propertyInfo1.setValue(params[0]);
            propertyInfo1.setType(String.class);

            request.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL, 80000);

            try {
                httpTransportSE.call(SOAP_ACTION, envelope);

                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

                xml = soapPrimitive.toString();

            } catch (SocketException ex) {
                Log.e("Error : ", "Error on soapPrimitiveData() " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception e) {
                Log.e("Error : ", "Error() " + e.getMessage());
                e.printStackTrace();
            }

            return xml;
        }
    }

    Stock MakeStockInstance(String stockNum, String stockPartCodeX, String bundleItems, String bundleNum, String bundleRemainder, String stockQty) {
        return new Stock(stockNum, stockPartCodeX, bundleItems, bundleNum, bundleRemainder, stockQty);
    }

    /**
     * List로 XML 객체를 String으로 가져옴
     */
    List<String> GetElementValue(String tagName, String str) {

        List<String> list = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
            Document doc = builder.parse(is);

            String setString = "";

            for (Node node : asList(doc.getElementsByTagName(tagName))) {
                setString += node.getChildNodes().item(0).getNodeValue() + "|"; // tagName의 첫번째
            }   // tagName의 value 30개가 |로 구분되어서 setString에 저장됨

            list = Arrays.asList(setString.split("\\|"));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return list;
    }
}