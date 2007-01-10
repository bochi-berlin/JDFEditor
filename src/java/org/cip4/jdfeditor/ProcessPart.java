package org.cip4.jdfeditor;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JComponent;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.node.JDFNode;

/*
 * FooProcessPart.java
 * @author SvenoniusI
 */

public class ProcessPart extends JComponent
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5703455772001305819L;

    public int rawWidth;
    public int rawHeight;
    public static final int PARENT=0;
    public static final int NODE=1;
    public static final int RESOURCE=2;
    public static final int RES_EXTERNAL=3;
    
    
    public boolean isSelected=false; // if true, this element is selected and is connected by emphasized ResourceLink lines
    private KElement elem; // the element (node or resource) that is displayed
    public int style; // the style of this ProcessPart, i.e Node, Parent Resource or Res_External
    
    private Color gColor;
    private String[] gString;    
    private Vector vInRes = new Vector();
    private Vector vOutRes = new Vector();
    private int xPos;
    private int yPos;

    private boolean isPositioned=false;

    static private Font procFont = null;
    static private Font resourceFont = null;
    static private Font parentFont = null;
    
    public ProcessPart(KElement _elem, int _style)
    {
        elem = _elem;
        style=_style;
        isSelected=_style==PARENT;
        setupFonts();
        
        switch (style)
        {
            case 0:
                setFont(parentFont);                
                break;
            case 1:
                setFont(procFont);                
                break;
            case 2:
                setFont(resourceFont);                
                break;
            case 3:
                setFont(resourceFont);                
                break;

            default:
                throw new IllegalArgumentException("bad style in constructor, mustt be in range 0-3: "+style);
        } 
        setStrings();
    }

    /**
     * set up the initial fonts based on inireader
     */
    private void setupFonts()
    {
        if(procFont==null)
        {
            INIReader reader=Editor.getIniFile();
            final String fontName=reader.getFontName();
            final int fontSize=reader.getFontSize();
            procFont = new Font(fontName, Font.PLAIN, fontSize);
            resourceFont = new Font(fontName, Font.PLAIN, fontSize-1);
            parentFont = new Font(fontName, Font.BOLD, fontSize+2);
        }
    }
    
    public void setPos(int _xPos, int _yPos)
    {
        xPos = _xPos;
        yPos = _yPos;
        isPositioned=true;
    }

    /**
     * 
     */
    private void setStrings()
    {
        if(elem instanceof JDFNode)
        {
            rawHeight = 75;
            
            if (style==PARENT)
                gColor = new Color(215, 245, 255);
            else
                gColor = new Color(180, 230, 250);
            
            JDFNode node = (JDFNode) elem;
            if (node.getType().equals(JDFConstants.COMBINED))
            {
                String[] tmp = { elem.getNodeName() + " " + elem.getAttribute("Type"),
                        elem.getAttribute("Types"),
                        elem.getAttribute("ID"),
                        elem.getAttribute("Status"),
                        node.getDescriptiveName()};
                gString = tmp;
            }
            else 
            {
                String[] tmp = { elem.getNodeName() + " " + elem.getAttribute("Type"),
                        elem.getAttribute("ID"),
                        elem.getAttribute("Status"),
                        elem.getAttribute("DescriptiveName")
                        };
                gString = tmp;
            }
           
            rawWidth = setPartWidth(gString);
            setToolTipText("JDFNode: "+elem.getAttribute("DescriptiveName"));
        }
        else if (style==RES_EXTERNAL)
        {
            rawHeight = 45;
            
            gColor = new Color(200, 250, 200);
            String[] tmp = { elem.getNodeName(),
                elem.getAttribute("ID") };
            gString = tmp;
            
            rawWidth = setPartWidth(gString);
            setToolTipText("JDFResource: "+elem.getAttribute("DescriptiveName"));
        }
        else 
        {
            rawHeight = 60;
            
            gColor = new Color(200, 250, 200);
            String[] tmp = { elem.getNodeName(),
                elem.getAttribute("ID"),
                elem.getAttribute("Status", "", "") };
            gString = tmp;
            
            rawWidth = setPartWidth(gString);
            setToolTipText("JDFResource: "+elem.getAttribute("DescriptiveName"));
        }
        rawWidth += 30;
    }
    
    private int setPartWidth(String[] s)
    {
        int w = 0;
        FontMetrics fm=getFM();
        for (int i = 0; i < s.length; i++)
        {
            w = w < fm.stringWidth(s[i]) ? fm.stringWidth(s[i]) : w;
        }        
        return w;
    }
        
    public KElement getElem()
    {
        return elem;
    }
        
    public Point getRightPoint()
    {
        final Point p = new Point(xPos + this.rawWidth, yPos + this.rawHeight / 2);        
        return p;
    }
    
    public Point getLeftPoint()
    {
        final Point p = new Point(xPos, yPos + this.rawHeight / 2);        
        return p;
    }
                       
    public Vector getvInRes()
    {
        return vInRes;
    }
        
    public void addTovInRes(ProcessPart pp)
    {
        vInRes.add(pp);
    }
    
    public Vector getvOutRes()
    {
        return vOutRes;
    }
        
    public void addTovOutRes(ProcessPart pp)
    {
        vOutRes.add(pp);
    }
        
    public Color getgColor()
    {
        return gColor;
    }
    
    public String[] getgString()
    {
        return this.gString;
    }
    
    public int getxPos()
    {
        return xPos;
    }
    
    public int getyPos()
    {
        return yPos;
    }
    public FontMetrics getFM()
    {
        return getFontMetrics(getFont());
    }

    /**
     * ProcessParts are equal if they contain the same element elem
     * also compares this.elem to a KElement 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object arg0)
    {
        if (super.equals(arg0))
            return true;
        if(arg0 instanceof ProcessPart)
            return elem.equals(((ProcessPart)arg0).elem);
        else if (arg0 instanceof KElement)
            return elem.equals(arg0);
        return false;
    }
    
    public String toString()
    {
        String s="[ProcessPart: ";
        if(elem!=null)
        {
            s+=" Name="+elem.getNodeName();
            String id=elem.getAttribute("ID",null,null);
            if(id!=null)
                s+=" ID="+id;
        }
        s+="["+super.toString()+ "]";
        return s;
    }

    /**
     * @return
     */
    public boolean hasPosition()
    {
        return isPositioned;
    }

    /**
     * @param b
     * @return
     */
    public VElement getPredecessors(boolean b, Vector parts)
    {
        final JDFNode jdfNode=(JDFNode)getElem();
        VElement v=jdfNode.getPredecessors(b, false);
        if(parts==null)
            return v;
        for(int i=v.size()-1;i>=0;i--)            
        {
            final int indexOf = parts.indexOf(new ProcessPart((KElement)v.elementAt(i),0));
            if(indexOf<0)
            {
                v.remove(i);
            }
        }
        return v;
    }
    
}