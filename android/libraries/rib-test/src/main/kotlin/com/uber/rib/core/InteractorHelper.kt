/*
 * Copyright (C) 2017. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uber.rib.core

import org.mockito.AdditionalMatchers.or
import org.mockito.Matchers.isA
import org.mockito.Matchers.isNull
import org.mockito.Mockito.verify

/** The helper to test [Interactor].  */
object InteractorHelper {
  /**
   * Attaches the [Interactor] using a mock router.
   *
   * @param <P> the type of presenter.
   * @param <R> the type of router.
   * @param interactor the [Interactor].
   * @param presenter the presenter for the [Interactor].
   * @param router the mock router for the [Interactor].
   * @param savedInstanceState the saved [Bundle].
   */
  @JvmStatic
  open fun <P : Any, R : Router<*>> attach(
    interactor: Interactor<P, R>,
    presenter: P,
    router: R,
    savedInstanceState: Bundle?
  ) {
    interactor.actualPresenter = presenter
    interactor.router = router
    interactor.dispatchAttach(savedInstanceState)
  }

  /**
   * Reattaches the [Interactor] without trying to set the router.
   *
   * @param interactor the [Interactor].
   * @param savedInstanceState the saved [Bundle].
   */
  @JvmStatic
  open fun reattach(interactor: Interactor<*, *>, savedInstanceState: Bundle?) {
    interactor.dispatchAttach(savedInstanceState)
  }

  /**
   * Detaches the [Interactor].
   *
   * @param controller the [Interactor].
   */
  @JvmStatic
  open fun detach(controller: Interactor<*, *>) {
    controller.dispatchDetach()
  }

  /**
   * Verifies that the [Interactor] is attached.
   *
   * @param interactor the [Interactor].
   */
  @JvmStatic
  open fun verifyAttached(interactor: Interactor<*, *>) {
    verify(interactor).dispatchAttach(or(isNull(Bundle::class.java), isA(Bundle::class.java)))
  }

  /**
   * Verifies that the [Interactor] is detached.
   *
   * @param interactor the [Interactor].
   */
  @JvmStatic
  open fun verifyDetached(interactor: Interactor<*, *>) {
    verify(interactor).dispatchDetach()
  }
}
