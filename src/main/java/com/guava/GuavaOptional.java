package com.guava;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
public class GuavaOptional
{
	private final static Map<String, String> stateCapitals;
	
	static
	{
		final Map<String, String> tempStatesToCapitals = Maps.newHashMap();
	    tempStatesToCapitals.put("Alaska", "Juneau");  
	    tempStatesToCapitals.put("Arkansas", "Little Rock");  
	    tempStatesToCapitals.put("Colorado", "Denver");  
	    tempStatesToCapitals.put("Idaho", "Boise");  
	    tempStatesToCapitals.put("Utah", "Salt Lake City");  
	    tempStatesToCapitals.put("Wyoming", "Cheyenne"); 
	    stateCapitals = Collections.unmodifiableMap(tempStatesToCapitals);
	}
	
	public Optional<String> getStateCapital(final String stateName)
	{
		return Optional.fromNullable(stateCapitals.get(stateName));
	}
	
	public Optional<BigDecimal> getQuotient(final BigDecimal dividend, final BigDecimal divisor)
	{
		BigDecimal quotient;
		try
		{
			quotient = dividend.divide(divisor);
		}
		catch(Exception ex)
		{
			System.out.println("unable to divide " + dividend + " by "+ divisor+"-"+ex.getMessage());
			quotient = null;
		}
		return Optional.fromNullable(quotient);
	}
	
	public static void main(String args[])
	{
		final GuavaOptional go = new GuavaOptional();
		final String wyoming = "Wyoming";
		final Optional<String> wyomingCapitalWrapper = go.getStateCapital(wyoming);
		
		if(wyomingCapitalWrapper.isPresent())
		{
			System.out.println("wyoming: "+ wyomingCapitalWrapper.get());
		}
		System.out.println("wyoming: "+ wyomingCapitalWrapper.orNull());
		
		final String northDakota = "North Dakota";
		final Optional<String> northDakotaCapitalWrapper = go.getStateCapital(northDakota);
		
		System.out.println("North Dakota: " + northDakotaCapitalWrapper);
		System.out.println("North Dakota: " + northDakotaCapitalWrapper.or("Unspecified"));
		System.out.println("North Dakota: " + northDakotaCapitalWrapper.orNull());
		
		final Optional<String> nullOptional = go.getStateCapital(null);
		System.out.println("null: "+ nullOptional);
		
		final BigDecimal dividend = new BigDecimal("5.0");
		final BigDecimal divisor = new BigDecimal("0.0");
		final Optional<BigDecimal> quotientWrapper = go.getQuotient(dividend, divisor);
		System.out.println(  "Quotient of " + dividend + " / " + divisor + " is "  + quotientWrapper);  
	}
	
}
