/***************************************************************************
 *   Copyright (C) by Fabrizio Montesi                                     *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU Library General Public License as       *
 *   published by the Free Software Foundation; either version 2 of the    *
 *   License, or (at your option) any later version.                       *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU Library General Public     *
 *   License along with this program; if not, write to the                 *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 *                                                                         *
 *   For details about the authors of this software, see the AUTHORS file. *
 ***************************************************************************/

package joliex.lang;


import jolie.Interpreter;
import jolie.net.CommMessage;
import jolie.runtime.FaultException;

public class Concurrent
{
	public CommMessage notify( CommMessage message )
		throws FaultException
	{
		Interpreter.getInstance().getLock(
					message.value().strValue()
				).notify();
		return CommMessage.createEmptyMessage();
	}
	
	public CommMessage notifyAll( CommMessage message )
		throws FaultException
	{
		Interpreter.getInstance().getLock(
					message.value().strValue()
				).notifyAll();
		return CommMessage.createEmptyMessage();
	}
	
	public CommMessage wait( CommMessage message )
		throws FaultException
	{
		try {
			Interpreter.getInstance().getLock(
					message.value().strValue()
				).wait();
		} catch( InterruptedException ie ) {}
		return CommMessage.createEmptyMessage();
	}
}
