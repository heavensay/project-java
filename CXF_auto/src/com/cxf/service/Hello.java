package com.cxf.service;

import javax.jws.WebService;


@WebService
public interface Hello {

	public String sayHello(String username);

}
