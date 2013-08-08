package conversion;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class Convertor extends JFrame implements ActionListener,DocumentListener{
	private JTextField tfDec;
	private JTextField tfBin;
	private JTextField tfOct;
	private JTextField tfHex;
	private JButton btnClear;
	private boolean chgByUser;
	private String strBin,strOct,strDec,strHex;
	int curOffset;
public Convertor(){
	//write code to initialize the frame.
	tfDec = new JTextField(17);
	tfBin = new JTextField(17);
	tfOct = new JTextField(17);
	tfHex = new JTextField(17);
	
	strBin=strOct=strDec=strHex="";
	curOffset=0;
	chgByUser = true;
	btnClear = new JButton("Clear");
	setLayout(new GridLayout(5,1));
	setSize(290,250);
	setMinimumSize(new Dimension(290,250));
	setMaximizedBounds(new Rectangle(280,240));
	setTitle("Base Convertor");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel row1 = new JPanel();
	row1.add(new JLabel("Binary  :"));row1.add(tfBin);
	add(row1);
	JPanel row2 = new JPanel();
	row2.add(new JLabel("Decimal :"));row2.add(tfDec);
	add(row2);
	JPanel row3 = new JPanel();
	row3.add(new JLabel("Octal     :"));row3.add(tfOct);
	add(row3);
	JPanel row4 = new JPanel();
	row4.add(new JLabel("Hex       :"));row4.add(tfHex);
	add(row4);
	add(btnClear);
	
	tfBin.getDocument().addDocumentListener(this);
	tfDec.getDocument().addDocumentListener(this);
	tfOct.getDocument().addDocumentListener(this);
	tfHex.getDocument().addDocumentListener(this);
	tfBin.getDocument().putProperty("name", "Binary");
	tfDec.getDocument().putProperty("name", "Decimal");
	tfOct.getDocument().putProperty("name", "Octal");
	tfHex.getDocument().putProperty("name", "Hex");
	btnClear.addActionListener(this);
	
	setVisible(true);
}
	
public static void main(String[] args) {
	new Convertor();

}

@Override
public void actionPerformed(ActionEvent arg0) {
	System.out.println(arg0.getSource().toString());
}

@Override
public void changedUpdate(DocumentEvent e) {
	//	detect(e);
}

@Override
public void insertUpdate(DocumentEvent e) {
	detect(e);
}

@Override
public void removeUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	detect(e);
}
public void detect(DocumentEvent e){

	Document doc =  e.getDocument();
	String nameProp = doc.getProperty("name").toString();
	curOffset=e.getOffset();
	if(nameProp.compareTo("Binary")==0){
		convertMe("bin",tfBin.getText().toString());
		}
	else if(nameProp.compareTo("Decimal")==0)
		convertMe("deci",tfDec.getText().toString());
	else if(nameProp.compareTo("Octal")==0)
		convertMe("oct",tfOct.getText().toString());
	else if(nameProp.compareTo("Hex")==0)
		convertMe("hex",tfHex.getText().toString());
	
}
public void convertMe(String loc,String tfCont){
	if(!chgByUser)
		return;
	
	if(loc.compareTo("bin")==0){
		chgByUser=false;
		if(tfCont.length()==0)
			tfCont="0";
		if(Binary.isBinStr(tfCont));
		else{
			tfBin.setText(strBin);
			chgByUser=false;
			return;}
		Binary bin=new Binary(tfCont);
		tfDec.setText(""+bin.toDecimal());
		tfOct.setText(bin.toOctal());
		tfHex.setText(bin.toHex());
		chgByUser=true;
		}
	
	else if(loc.compareTo("deci")==0){
		if(tfCont.length()==0)
			tfCont="0";
		if(Decimal.isDecimalStr(tfCont));
		else
			return;
		chgByUser=false;
		Decimal deci = new Decimal(Integer.parseInt(tfCont));
		
		tfBin.setText(deci.toBinary());
		tfOct.setText(deci.toOctal());
		tfHex.setText(deci.toHex());
		chgByUser=true;
	}
	else if(loc.compareTo("oct")==0){
		if(tfCont.length()==0)
			tfCont="0";
		if(Octal.isOctStr(tfCont));
		else
			return;
		chgByUser=false;
		Octal oct = new Octal(tfCont);
		tfBin.setText(oct.toBinary());
		tfDec.setText(""+oct.toDecimal());
		tfHex.setText(oct.toHex());
		chgByUser=true;
	}

	else if(loc.compareTo("hex")==0){
		if(tfCont.length()==0)
			tfCont="0";
		if(Hex.isHexString(tfCont));
		else{
			return;
			}
		chgByUser=false;
		Hex hex = new Hex(tfCont);
		tfBin.setText(hex.toBinary());
		tfOct.setText(hex.toOctal());
		tfDec.setText(""+hex.toDecimal());
		chgByUser=true;
	}
	strBin = tfBin.getText().toString();
	strOct = tfOct.getText().toString();
	strDec = tfDec.getText().toString();
	strHex = tfHex.getText().toString();
	return;
}
}
