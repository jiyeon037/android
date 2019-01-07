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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    EditText textBox;
    Button button;
    TextView text;

    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String METHOD_NAME = "GetStockInfo";
    private static final String SOAP_ACTION =
            "http://tempuri.org/StockInterface/GetStockInfo";
    private static final String URL = "http://211.233.61.250:8810/WCFAppLogic8810/Erp.BusinessManager.StockService";
    private NodeList nodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        textBox = (EditText) findViewById(R.id.textBox);
        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CallWebService().execute(textBox.getText().toString());
            }
        });
    }

    class CallWebService extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";

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

                result = soapPrimitive.toString();

            } catch (SocketException ex) {
                Log.e("Error : ", "Error on soapPrimitiveData() " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception e) {
                Log.e("Error : ", "Error() " + e.getMessage());
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String str) {

            String setString = "";
            List<List<String>> list = new ArrayList<>();

            list.add(GetElementValue("stockNum", str)); // tagName의 list(30개)를 List로 반환

            if(setString != null)
                text.setText(setString);
            else
                text.setText("Result is null");
        }
    }

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

            for(String s : list) {
                Log.i("aaaaaaaaaa", String.valueOf(s));
            }

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