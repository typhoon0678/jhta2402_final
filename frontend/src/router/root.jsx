import {lazy, Suspense} from "react";
import {createBrowserRouter} from "react-router-dom";

const Index = lazy(() => import("../pages/index"));
const SignIn = lazy(() => import('../pages/signin'));
const SignUp = lazy(() => import('../pages/signup'));

const root = createBrowserRouter([
    {
        path: '/',
        element: <Suspense><Index/></Suspense>
    },
    {
        path: '/login',
        element: <Suspense><SignIn/></Suspense>
    },
    {
        path: '/login',
        element: <Suspense><SignUp/></Suspense>
    },
])

export default root;