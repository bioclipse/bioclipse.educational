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
package net.bioclipse.pharmbio.comprepr.business;

import net.bioclipse.cdk.business.CDKManager;
import net.bioclipse.cdk.domain.ICDKMolecule;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.managers.business.IBioclipseManager;
import net.bioclipse.statistics.business.business.MatrixManager;
import net.bioclipse.statistics.model.IMatrixResource;

import org.openscience.cdk.graph.matrix.ConnectionMatrix;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.tools.IDCreator;

public class CompreprManager implements IBioclipseManager {

    private CDKManager cdk = new CDKManager();
    private MatrixManager matrix = new MatrixManager();
    
    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "comprepr";
    }

    public IMatrixResource connectivityMatrix(IMolecule molecule)
        throws BioclipseException {
        ICDKMolecule cdkMol = cdk.asCDKMolecule(molecule);
        IAtomContainer container = cdkMol.getAtomContainer();
        
        double[][] matrixVals = ConnectionMatrix.getMatrix(container);
        IMatrixResource connMatrix = matrix.create(matrixVals);
        int atomCount = 1;
        for (IAtom atom : container.atoms()) {
            String label = atom.getID();
            if (label == null) label = "";
            connMatrix.setColumnName(atomCount, label);
            connMatrix.setRowName(atomCount, label);
            atomCount++;
        }

        return connMatrix;
    }

    public IMolecule createIdentifiers(IMolecule molecule)
        throws BioclipseException {
        ICDKMolecule cdkMol = cdk.asCDKMolecule(molecule);
        IAtomContainer container = cdkMol.getAtomContainer();
        IDCreator.createIDs(container);
        return cdkMol;
    }
}
