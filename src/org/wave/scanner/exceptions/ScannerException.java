/*
   Copyright 2011 Benedito Barbosa Ribeiro Neto/Christian Linhares Peixoto/Mauricio da Silva Marinho

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.wave.scanner.exceptions;

import org.wave.scanner.enums.ErrorEnum;

/**
 * Excecao que apresenta as mensagens de erro da aplicacao.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * @author Mauricio Marinho
 * 
 * @see ErrorEnum
 * 
 */
public class ScannerException extends Exception {
	
//	TODO Rever todas as execoes da aplicacao (remover IllegalArgumentException)

	private static final long serialVersionUID = 1L;

	private ErrorEnum errorEnum;

	private Object[] params;

	public ScannerException(String message) {
		super(message);
	}

	public ScannerException(ErrorEnum errorEnum, Object... params) {
		this.errorEnum = errorEnum;
		this.params = params;
	}

	/**
	 * Retorna a mensagem de erro.
	 */
	@Override
	public String getMessage() {
		if (this.errorEnum == null) {
			return super.getMessage();
		}

		return this.errorEnum.getMessage(this.params);
	}

}
