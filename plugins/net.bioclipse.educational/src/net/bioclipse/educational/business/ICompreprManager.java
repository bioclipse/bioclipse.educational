/*******************************************************************************
 * Copyright (c) 2009  Egon Willighagen <egon.willighagen@gmail.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.educational.business;

import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.Recorded;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.managers.business.IBioclipseManager;
import net.bioclipse.statistics.model.IMatrixResource;

@PublishedClass(
    value="Manager with functionality specific for the Computer " +
    		"Representation chapter in the Pharmaceutical " +
    		"Bioinformatics book."
)
public interface ICompreprManager extends IBioclipseManager {

    @Recorded
    @PublishedMethod(methodSummary=
        "Creates a connectivity matrix for a IMolecule.",
        params="IMolecule molecule"
    )
    public IMatrixResource connectivityMatrix(IMolecule molecule);
    
    @Recorded
    @PublishedMethod(methodSummary=
        "Creates CML-style atom and bond identifiers.",
        params="IMolecule molecule"
    )
    public IMolecule createIdentifiers(IMolecule molecule);
    
}
