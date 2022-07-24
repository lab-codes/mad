package com.example.parsingdata;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    Button btnxml,btnjson;
    TextView txtxml,txtjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnxml=(Button)findViewById(R.id.btnxml);
        btnjson=(Button)findViewById(R.id.btnjson);
        txtxml=(TextView) findViewById(R.id.txtxml);
        txtjson=(TextView) findViewById(R.id.txtjson);
        btnxml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    InputStream is=getAssets().open("a.xml");
                    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                    DocumentBuilder db=dbf.newDocumentBuilder();
                    Document d=db.parse(is);
                    StringBuilder s=new StringBuilder();
                    s.append("XML DATA\n");
                    NodeList nodeList=d.getElementsByTagName("location");
                    for(int i=0;i<nodeList.getLength();i++)
                    {
                        Node node= nodeList.item(i);
                        if(node.getNodeType()==Node.ELEMENT_NODE)
                        {
                            Element element=(Element)node;
                            s.append("\n Place:").append(getValue("place",element));
                            s.append("\n Longitude:").append(getValue("longitude",element));
                            s.append("\n Latitude:").append(getValue("latitude",element));
                            s.append("\n Humidity:").append(getValue("humidity",element));

                        }
                    }
                    txtxml.setText(s.toString());

                }catch(Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnjson.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api= Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                String str=null;
                try {
                    InputStream is = getAssets().open("a.json");

                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    str = new String(buffer);
                    JSONObject j = new JSONObject(str);
                    JSONObject e = j.getJSONObject("location");
                    txtjson.setText("JSON DATA\n");
                    txtjson.append("\nPlace" + e.getString("place"));
                    txtjson.append("\nlongitude" + e.getString("longitude"));
                    txtjson.append("\nlatitude" + e.getString("latitude"));
                    txtjson.append("\nhumidity" + e.getString("humidity"));
                }catch(JSONException e) {
                    e.printStackTrace();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        });



    }
    private String getValue(String tag,Element element)
    {
        return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();

    }
}
