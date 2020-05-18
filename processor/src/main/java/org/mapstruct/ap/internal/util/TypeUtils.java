package org.mapstruct.ap.internal.util;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;

/**
 * TODO: javadoc
 *
 * @author Christian Bandowski
 */
public class TypeUtils {

    /**
     * Returns the type if it is a {@link DeclaredType}.
     *
     * @param type Type
     * @return Type as {@link DeclaredType} or {@code null}
     */
    public static DeclaredType asDeclaredType(TypeMirror type) {
        return type.accept(
            new SimpleTypeVisitor6<DeclaredType, Void>() {
                @Override
                public DeclaredType visitDeclared(DeclaredType t, Void p) {
                    return t;
                }
            },
            null
        );
    }

    /**
     * Returns the type if it is a {@link PrimitiveType}.
     *
     * @param type Type
     * @return Type as {@link PrimitiveType} or {@code null}
     */
    public static PrimitiveType asPrimitiveType(TypeMirror type) {
        return type.accept(
            new SimpleTypeVisitor6<PrimitiveType, Void>() {
                @Override
                public PrimitiveType visitPrimitive(PrimitiveType t, Void p) {
                    return t;
                }
            },
            null
        );
    }

    /**
     * Helper method, to obtain the fully qualified name of a declared or primitive type.
     *
     * @param type input type
     *
     * @return fully qualified name of type when the type is a {@link DeclaredType} or {@link PrimitiveType}, null
     * when otherwise.
     */
    public static String getQualifiedName(TypeMirror type) {
        DeclaredType declaredType = asDeclaredType( type );

        if ( declaredType != null ) {
            TypeElement typeElement = declaredType.asElement().accept(
                new SimpleElementVisitor6<TypeElement, Void>() {
                    @Override
                    public TypeElement visitType(TypeElement e, Void p) {
                        return e;
                    }
                },
                null
            );

            return typeElement != null ? typeElement.getQualifiedName().toString() : null;
        }

        PrimitiveType primitiveType = asPrimitiveType( type );

        if ( primitiveType != null ) {
            return primitiveType.toString();
        }

        return null;
    }
}
