package name.tomflucke.other;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable<Card>, Serializable
{
	private static final long serialVersionUID = -6933823033900874085L;

	public static byte CLUBS = 0;
	public static byte SPADES = 1;
	public static byte HEARTS = 2;
	public static byte DIAMONDS = 3;

	public final byte suit;
	public final short num;

	public static List<Card> buildDeck()
	{
		List<Card> result = new ArrayList<Card>();
		for (byte i = 0; i < 4; i++)
		{
			for (short j = 1; j <= 13; j++)
			{
				result.add(new Card(i, j));
			}
		}
		return result;
	}

	@SafeVarargs
	public static List<Card> shuffleDecks(List<Card>... decks)
	{
		List<Card> combined = new ArrayList<Card>();
		List<Card> result = new ArrayList<Card>();
		for (List<Card> deck : decks)
		{
			combined.addAll(deck);
		}
		for (Card c : combined)
		{
			result.add((int) (Math.random() * result.size()), c);
		}
		return result;
	}

	@SuppressWarnings("unused")
	private Card()
	{
		suit = -1;
		num = -1;
	}

	public Card(byte suit, int i) throws IllegalArgumentException
	{
		if (suit < 0 || suit > 3)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.suit = suit;
		}

		if (i > 13 || i < 1)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.num = (short) i;
		}
	}

	public Component toComp()
	{
		return new CardComponent();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Card)
		{
			Card other = (Card) obj;
			return other.num == this.num && other.suit == this.suit;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int compareTo(Card other)
	{
		return this.num - other.num;
	}

	@Override
	public String toString()
	{
		String strNum, strSuit;
		if (num == 11)
		{
			strNum = "Jack";
		}
		else if (num == 12)
		{
			strNum = "Queen";
		}
		else if (num == 13)
		{
			strNum = "King";
		}
		else if (num == 1)
		{
			strNum = "Ace";
		}
		else
		{
			strNum = String.valueOf(num);
		}
		if (suit == CLUBS)
		{
			strSuit = "Clubs";
		}
		else if (suit == SPADES)
		{
			strSuit = "Spades";
		}
		else if (suit == HEARTS)
		{
			strSuit = "Hearts";
		}
		else if (suit == DIAMONDS)
		{
			strSuit = "Diamonds";
		}
		else
		{
			strSuit = "";
		}
		return strNum + " of " + strSuit;
	}

	private class CardComponent extends Component
	{
		private static final long serialVersionUID = -7905463041589694877L;
		private static final double WToHRatio = 2.5 / 3.5;

		@Override
		public void paint(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.WHITE);
			Dimension dim;
			if (getHeight() < getWidth() / WToHRatio)
			{
				dim = new Dimension(getHeight(), (int) (getHeight() * WToHRatio));
			}
			else
			{
				dim = new Dimension((int) (getWidth() / WToHRatio), getWidth());
			}
			g2d.fillRoundRect(0, 0, dim.height, dim.width, dim.height / 25, dim.height / 25);
			g2d.setColor(suit <= 1 ? Color.BLACK : Color.RED);
			g2d.setFont(new Font(Font.SERIF, Font.BOLD, dim.height / 10));
			String drawNum;
			if (num == 11)
			{
				drawNum = "J";
			}
			else if (num == 12)
			{
				drawNum = "Q";
			}
			else if (num == 13)
			{
				drawNum = "K";
			}
			else if (num == 1)
			{
				drawNum = "A";
			}
			else
			{
				drawNum = String.valueOf(num);
			}
			int offsetH = dim.height / 15;
			int offsetW = (int) (dim.width / (15 * WToHRatio));
			g2d.drawString(drawNum, offsetH, offsetW);
			g2d.drawString(drawNum, offsetH, dim.width - offsetW);
			g2d.drawString(drawNum, dim.height - offsetH - g2d.getFont().getSize(), offsetW);
			g2d.drawString(drawNum, dim.height - offsetH - g2d.getFont().getSize(), dim.width
					- offsetW);
		}
	}
}
