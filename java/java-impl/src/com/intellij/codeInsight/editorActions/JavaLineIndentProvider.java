/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.codeInsight.editorActions;

import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.codeStyle.JavaLikeLangLineIndentProvider;
import com.intellij.psi.impl.source.codeStyle.SemanticEditorPosition;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Rustam Vishnyakov
 */
public class JavaLineIndentProvider extends JavaLikeLangLineIndentProvider {
  private final static HashMap<IElementType, SemanticEditorPosition.SyntaxElement> SYNTAX_MAP = new HashMap<>();
  static {
    SYNTAX_MAP.put(TokenType.WHITE_SPACE, JavaLikeElement.Whitespace);
    SYNTAX_MAP.put(JavaTokenType.SEMICOLON, JavaLikeElement.Semicolon);
    SYNTAX_MAP.put(JavaTokenType.LBRACE, JavaLikeElement.BlockOpeningBrace);
    SYNTAX_MAP.put(JavaTokenType.RBRACE, JavaLikeElement.BlockClosingBrace);
    SYNTAX_MAP.put(JavaTokenType.LBRACKET, JavaLikeElement.ArrayOpeningBracket);
    SYNTAX_MAP.put(JavaTokenType.RPARENTH, JavaLikeElement.RightParenthesis);
    SYNTAX_MAP.put(JavaTokenType.LPARENTH, JavaLikeElement.LeftParenthesis);
  }
  
  @Nullable
  @Override
  protected SemanticEditorPosition.SyntaxElement mapType(@NotNull IElementType tokenType) {
    return SYNTAX_MAP.get(tokenType);
  }
  
  @Override
  public boolean isSuitableForLanguage(@NotNull Language language) {
    return language.isKindOf(JavaLanguage.INSTANCE);
  }
}
